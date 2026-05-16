package com.greengea.service__catalogo__producto.controller;

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
import com.greengea.service__catalogo__producto.model.Producto;
import com.greengea.service__catalogo__producto.repository.ProductoRepository;
import com.greengea.service__catalogo__producto.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController 
{
    
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) 
    {
        Producto producto = categoriaService.buscarPorIdProducto(id);
        if (producto == null) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearProductoMensaje(@Valid @RequestBody Producto producto, BindingResult result) {

    if (result.hasErrors()) {

        String mensajeDelModelo = result.getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest().body(mensajeDelModelo);
    }
    productoRepository.save(producto);
    return ResponseEntity.ok("Guardado");
    }

    @PutMapping("/{id}") 
    public ResponseEntity<?> actualizarProducto(
            @PathVariable Long id, 
            @Valid@RequestBody Producto productoNuevo, BindingResult result) {

            if (result.hasErrors()) {
                String mensajeDelModelo = result.getFieldError().getDefaultMessage();
                return ResponseEntity.badRequest().body(mensajeDelModelo);
            }

            try{
                Producto productoActualizado = categoriaService.actualizarProducto(id, productoNuevo);
                    return ResponseEntity.ok(productoActualizado);
                }catch (RuntimeException e){
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        categoriaService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
