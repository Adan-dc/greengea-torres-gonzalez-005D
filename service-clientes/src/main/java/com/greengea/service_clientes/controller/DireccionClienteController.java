package com.greengea.service_clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greengea.service_clientes.model.DireccionCliente;
import com.greengea.service_clientes.repository.DireccionClienteRepository;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionClienteController {
    @Autowired
    private DireccionClienteRepository direccionClienteRepository;

    @PostMapping("/guardar")
    public DireccionCliente guardarFicha(@RequestBody DireccionCliente direccion) {
        return direccionClienteRepository.save(direccion);
    }

    @GetMapping("/listar")
    public List<DireccionCliente> listarFichas() {
        return direccionClienteRepository.findAll();
    }
}
