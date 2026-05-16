package com.greengea.service_inventario.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    @Min(value = 1, message = "Debe tener un minimo para reposicion")
    private int minimoParaReposicion;

    private Long productoId;

    @Transient
    private Object datosProducto; 

    @OneToMany(mappedBy = "stock")
    @JsonIgnore
    private List<Movimientos> movimientos;
}
