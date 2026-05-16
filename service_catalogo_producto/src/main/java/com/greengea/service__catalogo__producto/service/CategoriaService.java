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
    //buscarPorId producto
    public Producto buscarPorIdProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    //buscarPorId Categoria
    public Categoria buscarPorId(Long id){
        return categoriaRepository.findById(id).orElse(null);
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

        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            
            Categoria categoriaGuardada = categoria.get();

            
            categoriaGuardada.setNombre(categoriaNueva.getNombre());

            
            return categoriaRepository.save(categoriaGuardada);
        }

        return null; 
    }

    public void eliminar(Long id){
        categoriaRepository.deleteById(id);
    }

    //funciones del producto
    public Producto actualizarProducto(Long id, Producto productoNuevo) {

        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
        
            Producto productoGuardado = producto.get();

            
            productoGuardado.setCodigo(productoNuevo.getCodigo());
            productoGuardado.setNombre(productoNuevo.getNombre());
            productoGuardado.setPrecio_base(productoNuevo.getPrecio_base());
            productoGuardado.setPeso_gramos(productoNuevo.getPeso_gramos());
            productoGuardado.setDimensiones(productoNuevo.getDimensiones());

            return productoRepository.save(productoGuardado);
        }

        return null; 
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
    
    // conteo de productos dentro de una categoria
    public List<Object[]> obtenerConteoCategorias() {

        return categoriaRepository.conteoProductosPorCategoria();
    }
}
