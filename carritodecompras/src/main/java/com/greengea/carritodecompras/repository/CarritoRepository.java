package com.greengea.carritodecompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greengea.carritodecompras.model.Carrito;
import java.util.List;


public interface CarritoRepository extends JpaRepository<Carrito, Long>{
    // Trae todos los ID usuario
    List<Carrito> findByReferenciaC(Long referenciaC);

    // Traeme los items del carrito donde el ID coincida y además el tipo sea ""
    List<Carrito> findByReferenciaIdAndTipo(Long referenciaId, String tipo);

}
