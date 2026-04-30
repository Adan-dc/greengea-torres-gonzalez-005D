package com.greengea.carritodecompras.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.greengea.carritodecompras.model.Carrito;
import com.greengea.carritodecompras.repository.CarritoRepository;

@Service
public class CarritoService 
{
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    //Metodo eliminar item
    public void eliminar(Long id) {
        carritoRepository.deleteById(id);
    }
    //Metodo agregar item
    public Carrito agregarItem(Carrito carrito){
        return carritoRepository.save(carrito);
    }
    //Metodo actualizar carrito
    public Carrito actualizarCantidad(Long id, int cantidad){
        Carrito carrito = carritoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe el carrito")); //si no existe carrito en la bd devuelkkve null
        if(carrito != null){
            carrito.setCantidad(cantidad);
            return carritoRepository.save(carrito);
        }
        return null;
    }
    //Listar
    //Metodo obtener el carrito del cliente incluyendo la informacion de los 3 microservicios
    public List<Carrito> listarCarrito(Long clienteId) {

    List<Carrito> items = carritoRepository.findByReferenciaC(clienteId);
    // revisa uno por uno los productos del carro 
    for (Carrito item : items) {
        try {
            // PRODUCTO
            if ("producto".equalsIgnoreCase(item.getTipo())) {

                Object producto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/v1/productos/" + item.getReferenciaId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

                item.setDatosItems(producto);
                //el Map transforma a double el objeto con el dato de "Precio" del producto
                Map<String, Object> map = (Map<String, Object>) producto;
                Double precio_base = Double.valueOf(map.get("precio_base").toString()); //revisar esto con producto
                //Actualizamos el precio con el total que no se guarda en la base de datos
                item.setPrecio(precio_base);
                item.setTotal(precio_base * item.getCantidad());
            }
            // SERVICIO
            else if ("servicio".equalsIgnoreCase(item.getTipo())) {

                Object servicio = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/api/v1/servicios/" + item.getReferenciaId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

                item.setDatosItems(servicio);
                //el Map transforma a double el objeto con el dato de "Precio" del servicio
                Map<String, Object> map = (Map<String, Object>) servicio;
                Double precio_desde = Double.valueOf(map.get("precio_desde").toString());
                //Actualizamos el precio con el total que no se guarda en la base de datos
                item.setPrecio(precio_desde);
                item.setTotal(precio_desde * item.getCantidad());
            }

        } catch (Exception e) {
            item.setDatosItems("No disponible");
        }
    }
    return items;
    }
}
