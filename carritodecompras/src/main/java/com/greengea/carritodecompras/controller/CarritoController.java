package com.greengea.carritodecompras.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.greengea.carritodecompras.model.Carrito;
import com.greengea.carritodecompras.service.CarritoService;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
    
    //ELiminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //Agragar item
    @PostMapping
    public Carrito agregarItem(@RequestBody Carrito carrito){
        return carritoService.agregarItem(carrito);
    }

    //Actualizar                      //Camino            Extra
    @PutMapping("/{id}")              //a traves de Id  --< con el requestparam solo actualizo la cantidad
    public Carrito actualizarCantidad(@PathVariable Long id, @RequestParam int cantidad){
        return carritoService.actualizarCantidad(id, cantidad);
    }

    //Listar
    @GetMapping("/{ClienteId}")
    public List<Carrito> obtenerCarrito(@PathVariable Long clienteId){
        return carritoService.listarCarrito(clienteId);
    }
}
