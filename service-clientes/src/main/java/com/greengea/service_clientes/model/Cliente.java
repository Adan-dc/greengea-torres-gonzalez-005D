package com.greengea.service_clientes.model;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="cliente")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El rut es obligatorio")
    private String rut;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El email es obligatorio")
    private String email;
    
    private LocalDate fechaCreado;
    @Valid
    @OneToOne(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DireccionCliente direccionCliente;

}
