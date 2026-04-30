package com.greengea.service_clientes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name= "direccion_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionCliente 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String ciudad;
    private String comuna;
    private String region;
    private boolean es_principal;

    @OneToOne
    @JoinColumn(name= "cliente_id", unique =true)
    @JsonIgnore
    private Cliente cliente;
}
