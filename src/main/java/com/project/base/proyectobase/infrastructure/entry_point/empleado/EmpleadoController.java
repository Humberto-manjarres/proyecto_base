package com.project.base.proyectobase.infrastructure.entry_point.empleado;

import com.project.base.proyectobase.domain.model.empleado.Empleado;
import com.project.base.proyectobase.domain.usecase.empleado.EmpleadoUseCase;
import com.project.base.proyectobase.infrastructure.entry_point.empleado.mapper.EmpleadoDTO;
import com.project.base.proyectobase.infrastructure.entry_point.empleado.mapper.EmpleadoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoUseCase empleadoUseCase;

    private final EmpleadoTransformer empleadoTransformer;

    @GetMapping("/buscar-todos/{estado}")
    public ResponseEntity<Page<Empleado>> buscarTodosLosEmpleados(@PathVariable String estado, Pageable pageable) {
        Page<Empleado> empleadoList = empleadoUseCase.buscarTodosLosEmpleados(estado, pageable);
        return ResponseEntity.ok().body(empleadoList);
    }

    @GetMapping("/buscar-por-cedula/{cedula}")
    public ResponseEntity<EmpleadoDTO> consultarEmpleado(@PathVariable("cedula") String cedula){
        return ResponseEntity.ok(empleadoTransformer.empleadoToEmpleadoDTO(empleadoUseCase.consultarEmpleado(cedula)));
    }

    @PostMapping("/guardar-empleado")
    public ResponseEntity<EmpleadoDTO> guardarEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
        Empleado empleado = empleadoUseCase.guardarEmpleado(empleadoTransformer.empleadoDTOToEmpleado(empleadoDTO));
        return ResponseEntity.ok(empleadoTransformer.empleadoToEmpleadoDTO(empleado));
    }

    @PutMapping("/actualizar-empleado/{cedula}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable String cedula){
        Empleado empleadoActualizado = empleadoUseCase.actualizarEmpleado(empleadoTransformer.empleadoDTOToEmpleado(empleadoDTO),cedula);
        return ResponseEntity.ok().body(empleadoTransformer.empleadoToEmpleadoDTO(empleadoActualizado));
    }

    @PutMapping("/eliminar-empleado/{cedula}")
    public void eliminarEmpleado(@PathVariable String cedula){
        empleadoUseCase.eliminarEmpleado(cedula);
    }
}
