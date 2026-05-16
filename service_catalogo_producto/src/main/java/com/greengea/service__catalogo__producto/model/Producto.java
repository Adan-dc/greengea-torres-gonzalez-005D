package com.greengea.service__catalogo__producto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @Min(value = 4990, message = "El precio del producto debe ser mínimo 4990 pesos")
    private int precio_base;
    @Max(value = 50, message = "El peso del pedido debe ser maximo de 50 kilos")
    private float peso_gramos;
    @Max(value = 200, message = "El perimetro total del pedido debe ser maximo de 200 cm")
    private float dimensiones;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", unique = true)
    @JsonBackReference
    private Categoria categoria;
}
