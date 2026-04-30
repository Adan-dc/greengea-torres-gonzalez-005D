package com.greengea.service_inventario.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//clase stock(Tecnicamente la principal)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")// Esto le dice: "Si ya mostraste este ID, no te buclees"
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private int minimoParaReposicion;

    private Long productoId;

    @Transient // No se guarda en la base de datos db_gestion
    private Object datosProducto; 

    @OneToMany(mappedBy = "stock") // Se vincula con la variable de la otra clase

    private List<Movimientos> movimientos = new ArrayList<>();//esto tecnicamente es lo que te muestra la otra tabla dentro de esta
}
