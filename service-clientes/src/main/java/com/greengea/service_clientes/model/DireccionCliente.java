package com.greengea.service_clientes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "La calle es obligatoria")
    private String calle;
    @Min(value = 1, message = "Debe tener un numero de casa")
    private int numeroCasa;
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    @NotBlank(message = "El comuna es obligatoria")
    private String comuna;
    @NotBlank(message = "La region es obligatoria")
    private String region;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;
}
    
