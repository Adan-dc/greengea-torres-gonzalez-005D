package com.greengea.service__catalogo__producto.controller;

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
import com.greengea.service__catalogo__producto.model.Producto;
import com.greengea.service__catalogo__producto.repository.ProductoRepository;
import com.greengea.service__catalogo__producto.service.CategoriaService;

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

    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id, 
            @RequestBody Producto productoNuevo) {

        // Llamamos al servicio pasándole el ID de la URL y el JSON del Postman
        Producto productoActualizado = categoriaService.actualizarProducto(id, productoNuevo);

        if (productoActualizado != null) {
            // Si funcionó, devolvemos un 200 OK con los datos nuevecitos
            return ResponseEntity.ok(productoActualizado);
        } else {
            // Si el ID no existía en la BD, devolvemos un 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        categoriaService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
