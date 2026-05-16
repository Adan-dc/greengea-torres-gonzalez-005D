package com.greengea.service__catalogo__producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greengea.service__catalogo__producto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
