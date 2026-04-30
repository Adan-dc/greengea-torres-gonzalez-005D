package com.greengea.service_inventario.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//clase movimientos la clase hija
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id") // Esto le dice: "Si ya mostraste este ID, no te buclees"
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean tipo;//entrada o salida
    private int cantidad;
    private String motivo;
    private LocalDate fecha;

    private Long productoId;

    @ManyToOne//muchos a uno
    @JoinColumn(name = "stock_id")
    private Stock stock;//esto tecnicamente es lo que te muestra la otra tabla dentro de esta

    
    @Transient // No se guarda en la base de datos db_gestion
    private Object datosProducto; 
}
