package com.greengea.carritodecompras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String referenciaIdProducto;
    @Min(value = 1, message = "La cantidad del producto a adquirir debe ser mínimo 1")
    private Integer cantidad;
    private Integer precio_base;
    private Integer stock;
    private Integer subtotalProducto;
    //Esto no se guarda en la base de datos
    @Transient
    @JsonIgnore
    private Integer subtotalTotal;

}

