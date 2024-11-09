package com.project.base.proyectobase.domain.model.exception;

import java.util.function.Supplier;

public class BusinessException extends ApplicationException{

    public enum Type {

        EMPLEADO_NO_EXISTE("Empleado no existe!"),

        EMPLEADO_ELIMINADO("El empleado está inactivo"),

        USUARIO_NO_EXISTE("El usuario no existe"),

        USERNAME_YA_EXISTE("El username ya existe en Base de Datos"),

        PASSWORD_NO_COINCIDEN("La contraseña no coincide"),

        PASSWORD_INVALIDO("Contraseña invalida"),

        UPLOAD_ERROR("Error al guardar el archivo!"),

        ARCHIVO_VACIO("Debe adjuntar al menos un archivo");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public BusinessException create() {
            return new BusinessException(this);
        }

        public Supplier<BusinessException> asSupplier() {
            return () -> new BusinessException(this);
        }

    }

    private final Type type;

    public BusinessException(Type type){
        super(type.getMessage());
        this.type = type;
    }

}
