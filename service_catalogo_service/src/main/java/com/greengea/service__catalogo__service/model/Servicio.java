package com.greengea.service__catalogo__service.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;
    @NotBlank(message = "El tipo de servicio es obligatorio")
    private String tipo;
    @Min(value = 70588, message = "El precio se  mide en UTM, el valor minimo son 1UTM =  70588")
    private int precio_desde;
    private LocalDate tiempo_estimado_entrega;
    @NotBlank(message = "La descripcion es obligatoria ya que sirve de recordatorio del servicio a solicitar")
    private String descripcion;
}