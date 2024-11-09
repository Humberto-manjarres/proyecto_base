package com.project.base.proyectobase.infrastructure.driven_adapter.empleado;

import com.project.base.proyectobase.domain.model.empleado.Empleado;
import com.project.base.proyectobase.domain.model.empleado.gateway.EmpleadoGateway;
import com.project.base.proyectobase.domain.model.exception.BusinessException;
import com.project.base.proyectobase.infrastructure.driven_adapter.empleado.mapper.EmpleadoAdapterTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoGateway {

    private final static String DIRECTORIO_UPLOAD = "uploads";

    private final EmpleadoRepository empleadoRepository;

    private final EmpleadoAdapterTransformer empleadoAdapterTransformer;

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        EmpleadoEntity empleadoEntity = empleadoRepository.save(empleadoAdapterTransformer.empleadoToEmpleadoEntity(empleado));
        return empleadoAdapterTransformer.empleadoEntityToEmpleado(empleadoEntity);
    }

    @Override
    public Empleado consultarEmpleado(String cedula) {
        return empleadoRepository.findByCedula(cedula)
                .map(empleadoAdapterTransformer::empleadoEntityToEmpleado)
                .orElse(null);
    }

    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {
        EmpleadoEntity empleadoActualizado = empleadoRepository.save(empleadoAdapterTransformer.empleadoToEmpleadoEntity(empleado));
        return empleadoAdapterTransformer.empleadoEntityToEmpleado(empleadoActualizado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleadoAdapterTransformer.empleadoToEmpleadoEntity(empleado));
    }

    @Override
    public Page<Empleado> buscarTodos(Pageable pageable) {
        Page<EmpleadoEntity> empleadosActivos = empleadoRepository.findAll(pageable);
        return empleadosActivos.map(empleadoAdapterTransformer::empleadoEntityToEmpleado);
    }

    @Override
    public String copiar(MultipartFile archivo) {
        try {
            String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = getPath(nombreArchivo);
            // Crear el directorio si no existe
            Files.createDirectories(rutaArchivo.getParent());
            Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
            return nombreArchivo;
        }catch (IOException exception){
            throw new BusinessException(BusinessException.Type.UPLOAD_ERROR);
        }
    }

    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }

}
