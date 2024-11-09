package com.project.base.proyectobase.domain.auth.util;

import lombok.Getter;

@Getter
public enum RolePermission {

    CONSULTAR_TODOS_EMPLEADOS,
    CONSULTAR_UN_EMPLEADO,
    CREAR_UN_EMPLEADO,
    ACTUALIZAR_UN_EMPLEADO,
    ELIMINAR_UN_EMPLEADO,

    CONSULTAR_MY_PERFIL;
}
