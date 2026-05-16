package com.greengea.service_inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greengea.service_inventario.model.Movimientos;

public interface MovimientosRepository extends JpaRepository<Movimientos, Long>{
           @Query("""
              SELECT m.stock.id AS stockId, 
                     COUNT(m) AS cantidadMovimientos
              FROM Movimientos m 
              WHERE m.stock.id = :stockId
              GROUP BY m.stock.id
              """)
       List<Object[]> contarMovimientosPorStock(@Param("stockId") Long stockId);
}
