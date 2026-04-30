package com.greengea.service_catalogo_producto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greengea.service_catalogo_producto.model.Categoria;
import com.greengea.service_catalogo_producto.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<Categoria> listar(){
        return repository.findAll();
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria){
        return repository.save(categoria);
    }

}
