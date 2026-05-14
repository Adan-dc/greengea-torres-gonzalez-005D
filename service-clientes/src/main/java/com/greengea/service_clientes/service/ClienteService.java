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

    public Cliente actualizarCliente(Long id, Cliente clienteNuevo) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            Cliente clienteGuardado = cliente.get();

            clienteGuardado.setRut(clienteNuevo.getRut());
            clienteGuardado.setNombre(clienteNuevo.getNombre());
            clienteGuardado.setApellido(clienteNuevo.getApellido());
            clienteGuardado.setEmail(clienteNuevo.getEmail());
            clienteGuardado.setRol(clienteNuevo.getRol());
            clienteGuardado.setFechaCreado(clienteNuevo.getFechaCreado());
            if (clienteNuevo.getDireccionCliente() != null) {
                clienteGuardado.setDireccionCliente(clienteNuevo.getDireccionCliente());
            }
            return clienteRepository.save(clienteGuardado);
        }
        
        return null; 
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }


    //direccion
    public DireccionCliente guardarDireccion(DireccionCliente direccion) {
        
        if (direccion.getCliente() != null && direccion.getCliente().getId() != null) {
            
            Cliente cliente = clienteRepository.findById(direccion.getCliente().getId()).orElse(null);
            
            if (cliente != null) {
                // 2. Le chantamos el cliente oficial a la dirección
                direccion.setCliente(cliente);

                // 3. ¡GUARDAMOS SOLO LA DIRECCIÓN Y LISTO!
                // Eliminamos las líneas donde seteabas y guardabas al cliente.
                return direccionClienteRepository.save(direccion);
            }
        }
        return direccionClienteRepository.save(direccion);
    }

    public DireccionCliente actualizarDireccion(Long id, DireccionCliente direccionNueva) {

        Optional<DireccionCliente> direccionCliente = direccionClienteRepository.findById(id);

        if (direccionCliente.isPresent()) {
            DireccionCliente direccionClienteGuardada = direccionCliente.get();
            direccionClienteGuardada.setCalle(direccionNueva.getCalle());
            direccionClienteGuardada.setNumeroCasa(direccionNueva.getNumeroCasa());
            direccionClienteGuardada.setCiudad(direccionNueva.getCiudad());
            direccionClienteGuardada.setComuna(direccionNueva.getComuna());
            direccionClienteGuardada.setRegion(direccionNueva.getRegion());
            return direccionClienteRepository.save(direccionClienteGuardada);
        }
        return null; 
    }
    
    public void eliminarDireccion(Long id) {
        direccionClienteRepository.deleteById(id);
    }

    //Comentario para comprobar que se guardo bien :P
}
