package com.greengea.service_catalogo_servicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.greengea.service_catalogo_servicios.model.Servicio;





@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    // Buscar por tipo se servicio 
    Servicio findByTipo(String tipo);;


}
