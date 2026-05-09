package com.greengea.service_inventario.service;

import java.util.List;

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


    public Movimientos guardarMovimientos(Movimientos movimientos) {
    // 1. Validamos que el paciente existe ANTES de guardar
    // Esto evita crear atenciones para pacientes que no existen
    if (movimientos.getProductoId() != null) {
        try {
            Object datosProducto = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/producto/" + movimientos.getProductoId())
                .retrieve()
                .bodyToMono(Object.class)
                .block(); // Bloqueamos para asegurar la validación antes del save
            movimientos.setDatosProducto(datosProducto);
        } catch (Exception e) {
        // ¡MAGIA! Si el puerto 8082 está apagado, cae aquí. 
        // Ponemos un texto de aviso, pero NO detenemos el programa.
        movimientos.setDatosProducto("Microservicio de Catálogo offline. No se pudo cargar la info.");
        }
    }
    //if (movimientos.getStock() != null && movimientos.getStock().getId() != null) {
        //Stock stock = stockRepository.findById(movimientos.getStock().getId())
            //.orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        //movimientos.setStock(stock);
    //}

    // 3. Guardamos TODO el objeto ya validado y enriquecido
    // Al devolver 'atencion' después del save, JPA ya le asignó su ID
    return movimientosRepository.save(movimientos);
    }

    public Movimientos obtenerMovimientos(Long id) {
        Movimientos movimientos = movimientosRepository.findById(id).orElse(null);
        if (movimientos != null) {
            return enriquecerConProductos(movimientos);
        }
        return null;
    }

    public List<Movimientos> listarTodas() {
        return movimientosRepository.findAll();
    }

    public Stock guardarStock(Stock stock) {
    // Si envías solo el ID de la especialidad, JPA se encarga, 
    // pero es buena práctica validar o recuperar el objeto completo si quieres que la respuesta sea rica.
    return stockRepository.save(stock);
    }  
    
    public List<Stock> listarStock() {
    return stockRepository.findAll();
    }

    private Movimientos enriquecerConProductos(Movimientos movimientos) {
        if (movimientos.getProductoId() != null) {
            try {
                // Llamada al Microservicio de Pacientes (Puerto 8082)
                Object producto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/v1/producto/" + movimientos.getProductoId()) //Es el destinatario de tu mensaje
                    .retrieve() /*es el método que le dice a Spring: "Ya configuré la petición 
                    (URL, headers, etc.), ahora envíala y tráeme la respuesta" */
                    .bodyToMono(Object.class) /*Este método es el encargado de traducir el mensaje. Imagina que el microservicio
                    de Pacientes te responde en "idioma" JSON (texto plano), pero tu código Java necesita "objetos" para poder 
                     trabajar */
                    .block(); // Espera la respuesta
                
                movimientos.setDatosProducto(producto);//setDatosPaciente(paciente)
            } catch (Exception e) {
                // Si el micro de pacientes está caído, informamos en el campo pero no rompemos el flujo
                movimientos.setDatosProducto("Información de paciente no disponible actualmente");
            }
        }
        return movimientos;
    }
}
