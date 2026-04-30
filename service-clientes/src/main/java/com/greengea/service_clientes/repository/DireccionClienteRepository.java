package com.greengea.service_clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greengea.service_clientes.model.DireccionCliente;

@Repository
public interface DireccionClienteRepository extends JpaRepository<DireccionCliente, Long> {

    DireccionCliente findByCliente(DireccionCliente Direccion);

}
