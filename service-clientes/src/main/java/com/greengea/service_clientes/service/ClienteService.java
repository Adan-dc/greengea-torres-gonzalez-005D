package com.greengea.service_clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greengea.service_clientes.model.Cliente;
import com.greengea.service_clientes.model.DireccionCliente;
import com.greengea.service_clientes.repository.ClienteRepository;
import com.greengea.service_clientes.repository.DireccionClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DireccionClienteRepository direccionClienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente guardar(Cliente cliente) {
        if (cliente.getDireccionCliente() != null) {
            cliente.getDireccionCliente().setCliente(cliente);
        }
        return clienteRepository.save(cliente);
    }

    public DireccionCliente guardarDireccion(DireccionCliente direccion) {
        
        if (direccion.getCliente() != null && direccion.getCliente().getId() != null) {
            
            Cliente cliente = clienteRepository.findById(direccion.getCliente().getId()).orElse(null);
            
            if (cliente != null) {
                direccion.setCliente(cliente);      
                cliente.setDireccionCliente(direccion); 
                DireccionCliente direccionGuardada = direccionClienteRepository.save(direccion);
                clienteRepository.save(cliente);
                
                return direccionGuardada;
            }
        }
        return direccionClienteRepository.save(direccion);
    }
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
