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

import com.greengea.service__catalogo__producto.model.Categoria;
import com.greengea.service__catalogo__producto.service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController 
{
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaService.listarTodos();
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria){
        return categoriaService.guardar(categoria);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id, 
            @RequestBody Categoria categoriaNueva) {

        // Llamamos al servicio pasándole el ID de la URL y el JSON del Postman
        Categoria categoriaActualizada = categoriaService.actualizar(id, categoriaNueva);

        if (categoriaActualizada != null) {
            // Si funcionó, devolvemos un 200 OK con los datos nuevecitos
            return ResponseEntity.ok(categoriaActualizada);
        } else {
            // Si el ID no existía en la BD, devolvemos un 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
