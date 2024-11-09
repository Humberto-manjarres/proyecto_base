package com.project.base.proyectobase.infrastructure.entry_point.empleado.mapper;

import com.project.base.proyectobase.domain.model.empleado.Empleado;
import org.mapstruct.Mapper;

@Mapper
public interface EmpleadoTransformer {

    Empleado empleadoDTOToEmpleado(EmpleadoDTO empleadoDTO);
    EmpleadoDTO empleadoToEmpleadoDTO(Empleado empleado);

}
