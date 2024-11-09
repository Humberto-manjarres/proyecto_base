package com.project.base.proyectobase.infrastructure.driven_adapter.empleado.mapper;

import com.project.base.proyectobase.domain.model.empleado.Empleado;
import com.project.base.proyectobase.infrastructure.driven_adapter.empleado.EmpleadoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EmpleadoAdapterTransformer {

    Empleado empleadoEntityToEmpleado(EmpleadoEntity empleadoEntity);
    EmpleadoEntity empleadoToEmpleadoEntity(Empleado empleado);

}
