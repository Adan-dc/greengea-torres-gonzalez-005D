package com.greengea.carritodecompras.controller;

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
import com.greengea.carritodecompras.model.Carrito;
import com.greengea.carritodecompras.service.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
    

    @GetMapping
    public List<Carrito> listar(){
        return carritoService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> crearCarritoMensaje(@Valid @RequestBody Carrito carrito, BindingResult result) {

    if (result.hasErrors()) {

        String mensajeDelModelo = result.getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest().body(mensajeDelModelo);
    }
    carritoService.agregarProducto(carrito);
    return ResponseEntity.ok("Guardado");
    }

    @GetMapping("/totalP")
    public Integer obtenerTotal(){
        return carritoService.sumarSubtotalTotal();
    }

    @PutMapping("/{id}") 
    public ResponseEntity<?> actualizarCarrito(
            @PathVariable Long id, 
            @Valid@RequestBody Carrito carritoNuevo, BindingResult result) {

        if (result.hasErrors()) {
                String mensajeDelModelo = result.getFieldError().getDefaultMessage();
                return ResponseEntity.badRequest().body(mensajeDelModelo);
            }

            try{
                Carrito carritoActualizado = carritoService.actualizar(id, carritoNuevo);
                    return ResponseEntity.ok(carritoActualizado);
                }catch (RuntimeException e){
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
        }
}
