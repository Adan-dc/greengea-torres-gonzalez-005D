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
import com.greengea.service__catalogo__producto.model.Categoria;
import com.greengea.service__catalogo__producto.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController 
{
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/conteo")
    public List<Object[]> conteoCategorias() {

        return categoriaService.obtenerConteoCategorias();
    }

    @GetMapping
    public List<Categoria> listar(){
        return categoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) 
    {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearCategoriaMensaje(@Valid @RequestBody Categoria categoria, BindingResult result) {

    if (result.hasErrors()) {

        String mensajeDelModelo = result.getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest().body(mensajeDelModelo);
    }
    categoriaService.guardar(categoria);
    return ResponseEntity.ok("Guardado");
    }

    @PutMapping("/{id}") 
    public ResponseEntity<?> actualizarCategoria(
            @PathVariable Long id, 
            @Valid@RequestBody Categoria categoriaNueva, BindingResult result) {

        if (result.hasErrors()) {
                String mensajeDelModelo = result.getFieldError().getDefaultMessage();
                return ResponseEntity.badRequest().body(mensajeDelModelo);
            }

            try{
                Categoria categoriaActualizado = categoriaService.actualizar(id, categoriaNueva);
                    return ResponseEntity.ok(categoriaActualizado);
                }catch (RuntimeException e){
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
