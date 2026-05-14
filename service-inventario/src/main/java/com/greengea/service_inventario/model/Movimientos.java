package com.greengea.service_inventario.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Movimientos {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private boolean tipo;//entrada o salida
private int cantidad;
private String motivo;
private LocalDate fecha;

@Transient
private Object datosProducto; 

@ManyToOne
@JoinColumn(name = "stock_id")
private Stock stock;
}
