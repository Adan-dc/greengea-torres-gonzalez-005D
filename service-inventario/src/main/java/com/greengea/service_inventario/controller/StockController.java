package com.greengea.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public Stock crear(@RequestBody Stock stock) {
        return inventarioService.guardarStock(stock);
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
    public ResponseEntity<Stock> actualizarStock(
            @PathVariable Long id, 
            @RequestBody Stock stockNuevo) {
        
        Stock stockActualizado = inventarioService.actualizarStock(id, stockNuevo);

        if (stockActualizado != null) {
            return ResponseEntity.ok(stockActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminarStock(id);
        return ResponseEntity.noContent().build();
    }
}
