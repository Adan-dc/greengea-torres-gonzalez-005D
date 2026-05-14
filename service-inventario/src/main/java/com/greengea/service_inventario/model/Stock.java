package com.greengea.service_inventario.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Stock {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private int cantidad;
private int minimoParaReposicion;

private Long productoId;

@Transient
private Object datosProducto; 

@Transient
private Integer precioProducto;

@OneToMany(mappedBy = "stock")
@JsonIgnore
private List<Movimientos> movimientos;
}
