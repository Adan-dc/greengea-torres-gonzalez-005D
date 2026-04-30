package com.greengea.carritodecompras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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

    private Long referenciaC; // ID del cliente
    private Long referenciaId; // ID del producto o servicio
    private String tipo; // "PRODUCTO" o "SERVICIO"
    private int cantidad;
    //Esto no se guarda en la base de datos
    @Transient
    private Object datosItems;

    @Transient
    private Double precio;

    @Transient
    private Double total;

}

