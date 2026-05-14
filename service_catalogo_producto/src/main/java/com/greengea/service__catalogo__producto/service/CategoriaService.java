package com.greengea.service__catalogo__producto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greengea.service__catalogo__producto.model.Categoria;
import com.greengea.service__catalogo__producto.model.Producto;
import com.greengea.service__catalogo__producto.repository.CategoriaRepository;
import com.greengea.service__catalogo__producto.repository.ProductoRepository;

@Service
public class CategoriaService 
{
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<Categoria> listarTodos(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Categoria guardar(Categoria categoria) {
        if (categoria.getProductos() != null) {
            for (Producto producto : categoria.getProductos()) {
                producto.setCategoria(categoria);
            }
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria categoriaNueva) {

        // 1. Buscamos el stock en MySQL usando el ID de la URL
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            // 2. Lo sacamos del Optional
            Categoria categoriaGuardada = categoria.get();

            // 3. Le actualizamos los campos (¡NUNCA se actualiza el ID!)
            categoriaGuardada.setNombre(categoriaNueva.getNombre());

            // 4. Guardamos. Como el objeto ya tiene su ID original, Spring hace un UPDATE automático
            return categoriaRepository.save(categoriaGuardada);
        }

        // Si mandan un ID que no existe, devolvemos null
        return null; 
    }

    public void eliminar(Long id){
        categoriaRepository.deleteById(id);
    }

    //funciones del producto
    public Producto actualizarProducto(Long id, Producto productoNuevo) {

        // 1. Buscamos el stock en MySQL usando el ID de la URL
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            // 2. Lo sacamos del Optional
            Producto productoGuardado = producto.get();

            // 3. Le actualizamos los campos (¡NUNCA se actualiza el ID!)
            productoGuardado.setCodigo(productoNuevo.getCodigo());
            productoGuardado.setNombre(productoNuevo.getNombre());
            productoGuardado.setPrecio_base(productoNuevo.getPrecio_base());
            productoGuardado.setPeso_gramos(productoNuevo.getPeso_gramos());
            productoGuardado.setDimensiones(productoNuevo.getDimensiones());

            // 4. Guardamos. Como el objeto ya tiene su ID original, Spring hace un UPDATE automático
            return productoRepository.save(productoGuardado);
        }

        // Si mandan un ID que no existe, devolvemos null
        return null; 
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
