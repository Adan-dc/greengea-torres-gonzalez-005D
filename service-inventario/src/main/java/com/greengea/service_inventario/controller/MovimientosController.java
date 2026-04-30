package com.greengea.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greengea.service_inventario.model.Movimientos;
import com.greengea.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientosController {
    @Autowired
    private InventarioService inventarioService;
    @PostMapping
    public Movimientos crear(@RequestBody Movimientos movimientos) {
        return inventarioService.guardarMovimientos(movimientos);
    }
    @GetMapping("/{id}")
    public Movimientos verDetalle(@PathVariable Long id) {
        return inventarioService.obtenerMovimientos(id);
    }
    @GetMapping
    public List<Movimientos> listar() {
        return inventarioService.listarTodas();
    }
}
