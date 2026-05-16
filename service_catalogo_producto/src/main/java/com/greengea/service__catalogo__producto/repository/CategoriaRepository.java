package com.greengea.service__catalogo__producto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.greengea.service__catalogo__producto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Categoria findByNombre(String nombre);

    @Query("""
        SELECT c.nombre, COUNT(p)
        FROM Categoria c
        JOIN c.productos p
        GROUP BY c.nombre
        """)
    List<Object[]> conteoProductosPorCategoria();
    
}
