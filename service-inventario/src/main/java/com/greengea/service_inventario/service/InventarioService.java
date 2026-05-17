package com.greengea.service_inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.greengea.service_inventario.model.Movimientos;
import com.greengea.service_inventario.model.Stock;
import com.greengea.service_inventario.repository.MovimientosRepository;
import com.greengea.service_inventario.repository.StockRepository;

@Service
public class InventarioService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private MovimientosRepository movimientosRepository;

    //Movimientos
    public Movimientos guardarMovimientos(Movimientos movimientos){
        Optional<Stock> stockExistente = stockRepository.findById(movimientos.getStock().getId());
        if(stockExistente.isPresent()){
            Stock stock = stockExistente.get();
            if(movimientos.isTipo()){
                stock.setCantidad(stock.getCantidad() + movimientos.getCantidad());
                movimientosRepository.save(movimientos);
            }
            else if(movimientos.isTipo() == false && stock.getCantidad() >= movimientos.getCantidad()){
                stock.setCantidad(stock.getCantidad() - movimientos.getCantidad());
                movimientosRepository.save(movimientos);
            }
            else{
                throw new RuntimeException("No se puede sobrepasar la cantidad del movimiento a la del stock");
            }
            stockRepository.save(stock);
        }
        return null;
    }
    
    public List<Movimientos> listarTodas() {
        return movimientosRepository.findAll();
    }
    public Optional<Movimientos> buscarMovimientoPorId(Long id){
        return movimientosRepository.findById(id);
    }

    public Movimientos actualizarMovimientos(Long id, Movimientos movimientosNuevo) {
        
        Optional<Movimientos> movimientos = movimientosRepository.findById(id);
        if (movimientos.isPresent()) {
            Movimientos movimientosGuardado = movimientos.get();
            Stock stock = movimientosGuardado.getStock();

                if(movimientosNuevo.isTipo()){
                    stock.setCantidad(stock.getCantidad() + movimientosNuevo.getCantidad());
                    movimientosGuardado.setCantidad(movimientosNuevo.getCantidad());
                }
                else if(movimientosNuevo.isTipo() == false && stock.getCantidad() >= movimientosNuevo.getCantidad()){
                    stock.setCantidad(stock.getCantidad() - movimientosNuevo.getCantidad());
                    movimientosGuardado.setCantidad(movimientosNuevo.getCantidad());
                }
                else{
                    throw new RuntimeException("No se puede sobrepasar la cantidad del movimiento a la del stock");
                }
            stockRepository.save(stock);

            movimientosGuardado.setTipo(movimientosNuevo.isTipo());
            movimientosGuardado.setMotivo(movimientosNuevo.getMotivo());
            movimientosGuardado.setFecha(movimientosNuevo.getFecha());


            return movimientosRepository.save(movimientosGuardado);
        }
        
        return null; 
    }

    public void eliminarMovimientos(Long id) {
        movimientosRepository.deleteById(id);
    }

    //Stock

    public Stock guardarStock(Stock stock) {
    if (stock.getProductoId() != null) {
        try {
            Object datosProducto = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/v1/producto/" + stock.getProductoId())
                .retrieve()
                .bodyToMono(Object.class)
                .block(); 
            stock.setDatosProducto(datosProducto);
        } catch (Exception e) {
        stock.setDatosProducto("Microservicio de Catálogo offline. No se pudo cargar la info.");
        }
    }
        return stockRepository.save(stock);
    }
    
    public List<Stock> listarStock() {
        List<Stock> listaStock = stockRepository.findAll();
        for (Stock stock : listaStock) {
            enriquecerConProductos(stock); 
        }
        return listaStock;
    }

    public Stock obtenerStock(Long id) {
        Stock stock = stockRepository.findById(id).orElse(null);
        if (stock != null) {
            return enriquecerConProductos(stock);
        }
        return null;
    }

    public Stock actualizarStock(Long id, Stock stockNuevo) {
        Optional<Stock> stock = stockRepository.findById(id);

        if (stock.isPresent()) {
            Stock stockGuardado = stock.get();
            stockGuardado.setCantidad(stockNuevo.getCantidad());
            stockGuardado.setMinimoParaReposicion(stockNuevo.getMinimoParaReposicion());
            return stockRepository.save(stockGuardado);
        }
        return null; 
    }
    
    public void eliminarStock(Long id) {
        stockRepository.deleteById(id);
    }
    private Stock enriquecerConProductos(Stock stock) {
        if (stock.getProductoId() != null) {
            try {
                Object producto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8085/api/v1/producto/" + stock.getProductoId())
                    .retrieve() 
                    .bodyToMono(Object.class) 
                    .block(); 
                
                stock.setDatosProducto(producto);
                if (stock.getMovimientos() != null) {
                for (Movimientos mov : stock.getMovimientos()) {
                    mov.setDatosProducto(producto);
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR AL LLAMAR AL PRODUCTO: " + e.getMessage());
                stock.setDatosProducto("Información del producto no disponible actualmente");
            }
        }
        return stock;
    }
    public List<Object[]> contarMovimientosDeStock(Long stockId) {
        return movimientosRepository.contarMovimientosPorStock(stockId);
    }

}
