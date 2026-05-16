package com.greengea.service_inventario.controller;

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
import com.greengea.service_inventario.model.Stock;
import com.greengea.service_inventario.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<?> crearStock(@Valid @RequestBody Stock stock, BindingResult result) {
    
        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }
        inventarioService.guardarStock(stock);
        return ResponseEntity.ok("Guardado con éxito");
    }

    @GetMapping
    public List<Stock> listar() {
        return inventarioService.listarStock();
    }
    

    @GetMapping("/{id}")
    public Stock verDetalle(@PathVariable Long id) {
        return inventarioService.obtenerStock(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarStock(@PathVariable Long id, @Valid @RequestBody Stock stockActualizado, BindingResult result) {

        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }

        try {
            Stock guardado = inventarioService.actualizarStock(id, stockActualizado);

            return ResponseEntity.ok(guardado);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminarStock(id);
        return ResponseEntity.noContent().build();
    }
}
