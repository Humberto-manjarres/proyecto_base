package com.project.base.proyectobase.domain.model.empleado.gateway;

import com.project.base.proyectobase.domain.model.empleado.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmpleadoGateway {

    Empleado guardarEmpleado(Empleado empleado);

    Empleado consultarEmpleado(String cedula);

    Empleado actualizarEmpleado(Empleado empleado);

    void eliminarEmpleado(Empleado empleado);

    Page<Empleado> buscarTodos(Pageable pageable);

    String copiar(MultipartFile archivo);
}
