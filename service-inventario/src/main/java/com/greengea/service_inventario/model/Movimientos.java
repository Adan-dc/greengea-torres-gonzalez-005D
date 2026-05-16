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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    @NotNull(message = "El tipo no puede estar vacío, manda true o false")
    private boolean tipo;//Compra o venta
    @Min(value = 1, message = "La cantidad del movimiento debe ser mínimo 1")
    private int cantidad;
    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;
    private LocalDate fecha;

    @Transient
    @JsonIgnore
    private Object datosProducto; 
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
