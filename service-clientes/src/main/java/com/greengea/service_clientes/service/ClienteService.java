package com.greengea.service_clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greengea.service_clientes.model.Cliente;
import com.greengea.service_clientes.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente guardar(Cliente cliente) {
        // Lógica: Si el paciente viene con una ficha, debemos 
        // asegurar que la ficha conozca a su paciente para que JPA lo guarde bien.
        if (cliente.getDireccionCliente() != null) {
            cliente.getDireccionCliente().setCliente(cliente);;
        }
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
