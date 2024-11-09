package com.project.base.proyectobase.domain.usecase.upload;

import com.project.base.proyectobase.domain.model.empleado.Empleado;
import com.project.base.proyectobase.domain.model.empleado.gateway.EmpleadoGateway;
import com.project.base.proyectobase.domain.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RequiredArgsConstructor
public class UploadUseCase {

    private final String INACTIVO = "INACTIVO";

    private final EmpleadoGateway empleadoGateway;

    public String cargarArchivo(MultipartFile archivo, String cedula) {
        Empleado empleado = empleadoGateway.consultarEmpleado(cedula);
        this.validarEmpleado(empleado);
        if (Objects.isNull(archivo)) throw new BusinessException(BusinessException.Type.ARCHIVO_VACIO);
        return empleadoGateway.copiar(archivo);
    }

    private void validarEmpleado(Empleado empleado){
        if (Objects.isNull(empleado)) throw new BusinessException(BusinessException.Type.EMPLEADO_NO_EXISTE);
        if (empleado.getEstado().equals(INACTIVO)) throw new BusinessException(BusinessException.Type.EMPLEADO_ELIMINADO);
    }

}
