package com.greengea.service__catalogo__service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greengea.service__catalogo__service.model.Servicio;
import com.greengea.service__catalogo__service.repository.ServicioRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicioService 
{
    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> listarTodos(){
        return servicioRepository.findAll();
    } 

    public Servicio buscarPorId(Long id){
        return servicioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Servicio guardar(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio actualizar(Long id, Servicio servicioNuevo) {

        Optional<Servicio> servicio = servicioRepository.findById(id);

        if (servicio.isPresent()) {

            Servicio servicioGuardado = servicio.get();

            servicioGuardado.setCodigo(servicioNuevo.getCodigo());
            servicioGuardado.setTipo(servicioNuevo.getTipo());
            servicioGuardado.setPrecio_desde(servicioNuevo.getPrecio_desde());
            servicioGuardado.setTiempo_estimado_entrega(servicioNuevo.getTiempo_estimado_entrega());
            servicioGuardado.setDescripcion(servicioNuevo.getDescripcion());

            return servicioRepository.save(servicioGuardado);
        }
        return null; 
    }
    
    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }
}
