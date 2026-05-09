package com.greengea.service_catalogo_producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greengea.service_catalogo_producto.model.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    // manualidades, pepeleria, etc
    Categoria findByNombre(String nombre);
    // buscar por nombre ignorando mayuscula/minuscula
    Categoria findByNombreIgnoreCase(String nombre);
}
