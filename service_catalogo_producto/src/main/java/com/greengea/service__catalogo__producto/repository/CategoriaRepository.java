package com.greengea.service__catalogo__producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greengea.service__catalogo__producto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // manualidades, pepeleria, etc
    Categoria findByNombre(String nombre);
    // buscar por nombre ignorando mayuscula/minuscula
    Categoria findByNombreIgnoreCase(String nombre);
}
