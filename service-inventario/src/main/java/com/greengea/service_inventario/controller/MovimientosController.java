package com.greengea.service_inventario.controller;
import com.greengea.service_inventario.repository.MovimientosRepository;
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
import org.springframework.web.bind.annotation.RestController;
import com.greengea.service_inventario.model.Movimientos;
import com.greengea.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientosController {
    private MovimientosRepository movimientosRepository;
    @Autowired
    private InventarioService inventarioService;
    @PostMapping
    public Movimientos crear(@RequestBody Movimientos movimientos) {
        return inventarioService.guardarMovimientos(movimientos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> obtener(@PathVariable Long id) {
        return inventarioService.buscarMovimientoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Movimientos> listar() {
        return movimientosRepository.findAll();
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Movimientos> actualizarMovimientos(
            @PathVariable Long id, 
            @RequestBody Movimientos movimientoNuevo) {

        Movimientos movimientosActualizado = inventarioService.actualizarMovimientos(id, movimientoNuevo);

        if (movimientosActualizado != null) {
            return ResponseEntity.ok(movimientosActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminarMovimientos(id);
        return ResponseEntity.noContent().build();
    }
}
