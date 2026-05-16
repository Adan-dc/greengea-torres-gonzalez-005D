package com.greengea.service_inventario.controller;
import com.greengea.service_inventario.repository.MovimientosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.greengea.service_inventario.model.Movimientos;
import com.greengea.service_inventario.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientosController {
    @Autowired
    private MovimientosRepository movimientosRepository;
    @Autowired
    private InventarioService inventarioService;
    
    @PostMapping
    public ResponseEntity<?> crearMovimiento(@Valid @RequestBody Movimientos movimiento, BindingResult result) {
    
        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }

        try {
            Movimientos guardado = inventarioService.guardarMovimientos(movimiento);
            return ResponseEntity.ok(guardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> obtener(@PathVariable Long id) {
        return inventarioService.buscarMovimientoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Movimientos> listar() {
        return movimientosRepository.findAll();
    }

    @GetMapping("/conteo/{stockId}")
    public ResponseEntity<?> verConteoDeMovimientos(@PathVariable Long stockId) {
        
        List<Object[]> resultado = inventarioService.contarMovimientosDeStock(stockId);

        if (resultado == null || resultado.isEmpty()) {
            return ResponseEntity.ok("No hay movimientos para este stock o el stock no existe.");
        }

        return ResponseEntity.ok(resultado);
    }
    @PutMapping("/{id}") 
    public ResponseEntity<?> actualizarMovimientos(@PathVariable Long id, @Valid @RequestBody Movimientos movimiento, BindingResult result) {
    
        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }

        try {
            Movimientos guardado = inventarioService.actualizarMovimientos(id, movimiento);
            return ResponseEntity.ok(guardado);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminarMovimientos(id);
        return ResponseEntity.noContent().build();
    }
}
