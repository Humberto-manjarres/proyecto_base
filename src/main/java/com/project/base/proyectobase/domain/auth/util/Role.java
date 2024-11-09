package com.project.base.proyectobase.domain.auth.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;



@Getter
public enum Role {
    USER(Arrays.asList(
            RolePermission.CONSULTAR_MY_PERFIL
    )),
    ADMIN(Arrays.asList(
            RolePermission.CONSULTAR_TODOS_EMPLEADOS,
            RolePermission.ACTUALIZAR_UN_EMPLEADO,
            RolePermission.CONSULTAR_UN_EMPLEADO,
            RolePermission.CREAR_UN_EMPLEADO,
            RolePermission.ELIMINAR_UN_EMPLEADO,
            RolePermission.CONSULTAR_MY_PERFIL
    ));

    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
