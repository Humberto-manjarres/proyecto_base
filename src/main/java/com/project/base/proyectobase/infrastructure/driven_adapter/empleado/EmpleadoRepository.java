package com.project.base.proyectobase.infrastructure.driven_adapter.empleado;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {

    Optional<EmpleadoEntity> findByCedula(String cedula);
    List<EmpleadoEntity> findByEstado(String estado);

}
