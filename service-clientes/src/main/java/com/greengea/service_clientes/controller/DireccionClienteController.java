package com.greengea.service_clientes.controller;

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
import com.greengea.service_clientes.model.DireccionCliente;
import com.greengea.service_clientes.repository.DireccionClienteRepository;
import com.greengea.service_clientes.service.ClienteService;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionClienteController {
    @Autowired
    private DireccionClienteRepository direccionClienteRepository;
    @Autowired
    private ClienteService clienteService;
    @PostMapping
    public DireccionCliente guardarDireccion(@RequestBody DireccionCliente direccion) {
        return clienteService.guardarDireccion(direccion);
    }

    @GetMapping
    public List<DireccionCliente> listarDireccion() {
        return direccionClienteRepository.findAll();
    }

    @PutMapping("/{id}") 
    public ResponseEntity<DireccionCliente> actualizarDireccion(
        @PathVariable Long id, 
        @RequestBody DireccionCliente direccionNueva) {

        DireccionCliente direccionActualizado = clienteService.actualizarDireccion(id, direccionNueva);

        if (direccionActualizado != null) {
            return ResponseEntity.ok(direccionActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
