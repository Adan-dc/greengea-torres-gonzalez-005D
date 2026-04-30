package com.greengea.service_catalogo_producto.service;

import com.greengea.service_catalogo_producto.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greengea.service_catalogo_producto.model.Categoria;
import com.greengea.service_catalogo_producto.model.Producto;
import com.greengea.service_catalogo_producto.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    private final CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    ProductoService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id){
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto guardar(Producto producto){
        
        Long categoriaId = producto.getCategoria().getId();

        Categoria categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new RuntimeException("Categoria no existe"));
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    public void eliminar(Long id){
        productoRepository.deleteById(id);
    }
}
