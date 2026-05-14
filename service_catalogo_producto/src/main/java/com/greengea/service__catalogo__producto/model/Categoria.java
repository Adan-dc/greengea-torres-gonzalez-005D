package com.greengea.service__catalogo__producto.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre; //Ej: Encuaderncion, Papeleria, Manualidades

    //Al usar uno a muchos es necesario usar el List para obtener todos los productos de una categoria
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    @JsonManagedReference
    //Como una categoria puede tener muchos productos 
    private List<Producto> productos = new ArrayList<>(); 
    
}
