package com.greengea.service_catalogo_servicios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greengea.service_catalogo_servicios.model.Servicio;
import com.greengea.service_catalogo_servicios.repository.ServicioRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicioService 
{
    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> listarTodos(){
        return servicioRepository.findAll();
    } 

    public Optional<Servicio> buscarPorId(Long id) {
        return servicioRepository.findById(id);
    }

    @Transactional
    public Servicio guardar(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }
}
