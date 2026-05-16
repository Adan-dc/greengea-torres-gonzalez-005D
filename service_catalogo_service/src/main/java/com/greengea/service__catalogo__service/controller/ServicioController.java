package com.greengea.service__catalogo__service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.greengea.service__catalogo__service.model.Servicio;
import com.greengea.service__catalogo__service.service.ServicioService;

import jakarta.validation.Valid;

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
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) 
    {
        Servicio servicio = servicioService.buscarPorId(id);
        if (servicio == null) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearServicio(@Valid @RequestBody Servicio servicio, BindingResult result) {

        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }
        try {
            Servicio guardado = servicioService.guardar(servicio);
            return ResponseEntity.ok(guardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}") 
    public ResponseEntity<?> actualizarservicio(
            @PathVariable Long id, 
            @Valid@RequestBody Servicio servicioNuevo, BindingResult result) {

        if (result.hasErrors()) {
                String mensajeDelModelo = result.getFieldError().getDefaultMessage();
                return ResponseEntity.badRequest().body(mensajeDelModelo);
            }

            try{
                Servicio servicioActualizado = servicioService.actualizar(id, servicioNuevo);
                    return ResponseEntity.ok(servicioActualizado);
                }catch (RuntimeException e){
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
