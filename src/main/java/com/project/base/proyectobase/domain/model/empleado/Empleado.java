package com.project.base.proyectobase.domain.model.empleado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    private Integer id;
    private String cedula;
    private String nombre;
    private String estado;
}
