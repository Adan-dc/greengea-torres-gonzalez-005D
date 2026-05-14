package com.greengea.service__catalogo__service.controller;

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

import com.greengea.service__catalogo__service.model.Servicio;
import com.greengea.service__catalogo__service.service.ServicioService;

@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController 
{
    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> listar() {
        return servicioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtener(@PathVariable Long id) {
        return servicioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servicio> crear(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.guardar(servicio));
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Servicio> actualizarservicio(
            @PathVariable Long id, 
            @RequestBody Servicio servicioNuevo) {

        Servicio servicioActualizado = servicioService.actualizar(id, servicioNuevo);

        if (servicioActualizado != null) {

            return ResponseEntity.ok(servicioActualizado);
        } else {
   
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
