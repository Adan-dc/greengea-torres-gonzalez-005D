package com.greengea.service_clientes.controller;

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

import com.greengea.service_clientes.model.Cliente;
import com.greengea.service_clientes.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
    
        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }
        clienteService.guardar(cliente);
        return ResponseEntity.ok("Guardado con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteActualizado, BindingResult result) {
        if (result.hasErrors()) {
            String mensajeDelModelo = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(mensajeDelModelo);
        }
        try {
            Cliente guardado = clienteService.actualizarCliente(id, clienteActualizado);

            return ResponseEntity.ok(guardado); 
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
