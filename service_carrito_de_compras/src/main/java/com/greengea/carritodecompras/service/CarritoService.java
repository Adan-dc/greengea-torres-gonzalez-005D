package com.greengea.carritodecompras.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public List<Carrito> listarTodos(){
        return carritoRepository.findAll();
    }
    
    public Carrito agregarProducto(Carrito carrito) {
        try {
            if (carrito.getReferenciaIdProducto() != null) {

                 Map producto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8087/api/v1/stock/"
                            + carrito.getReferenciaIdProducto())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

                 Map precio = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8085/api/v1/producto/"
                            + carrito.getReferenciaIdProducto())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

                if (producto == null) {
                    throw new RuntimeException("Producto no encontrado");
                }

                if (precio == null) {
                    throw new RuntimeException("Producto no encontrado");
                }

                    Integer stock = ((Integer) producto.get("cantidad")).intValue();

                    Integer precioP = ((Integer) precio.get("precio_base")).intValue();

                    if (carrito.getCantidad() > stock) {
                        throw new RuntimeException("No hay stock suficiente");
                    }

                carrito.setStock(stock);
                carrito.setPrecioUnitario(precioP);
                int subtotal = carrito.getCantidad() * carrito.getPrecioUnitario();
                carrito.setSubtotalProducto(subtotal);
            }

            return carritoRepository.save(carrito);

        } catch (Exception e) {
           throw new RuntimeException("Microservicio de Inventario offline o producto no encontrado");
        }
    }

    public void eliminar(Long id) {
        carritoRepository.deleteById(id);
    }

    public Carrito actualizar(Long id, Carrito carritoNuevo) {

        Optional<Carrito> carrito = carritoRepository.findById(id);

        if (carrito.isPresent()) {

            Carrito carritoGuardado = carrito.get();

            carritoGuardado.setReferenciaIdProducto(carritoNuevo.getReferenciaIdProducto());
            carritoGuardado.setCantidad(carritoNuevo.getCantidad());

            int nuevoSubtotal = carritoGuardado.getCantidad() * carritoGuardado.getPrecioUnitario();
            carritoGuardado.setSubtotalProducto(nuevoSubtotal);
            
            return carritoRepository.save(carritoGuardado);
        }
        return null; 
    }
   
    public int sumarSubtotalTotal(){
       int  subtotalTotal = 0;
       for (Carrito carrito : carritoRepository.findAll()){ 
            if (carrito.getSubtotalProducto() != null) {     
            subtotalTotal += carrito.getSubtotalProducto();
          }
        }
       return subtotalTotal;
    }
}
