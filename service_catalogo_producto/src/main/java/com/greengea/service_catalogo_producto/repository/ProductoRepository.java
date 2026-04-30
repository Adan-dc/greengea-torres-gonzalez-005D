package com.greengea.service_catalogo_producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greengea.service_catalogo_producto.model.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    //buscar por codigo del producto
    Producto findByCodigo(String codigo);
    // Reporte: Producto por tipo de categoria
    @Query("""
            SELECT p.categoria.nombre as categoria,
                COUNT(p) as cantidad
            FROM Producto p
            GROUP BY p.categoria.nombre
            """)
    List<Object[]> conteoPorCategoria();
}
