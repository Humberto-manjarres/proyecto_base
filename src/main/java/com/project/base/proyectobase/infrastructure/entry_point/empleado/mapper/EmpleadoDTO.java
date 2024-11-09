package com.project.base.proyectobase.infrastructure.entry_point.empleado.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private Integer id;
    private String cedula;
    private String nombre;
    private String estado;
}
