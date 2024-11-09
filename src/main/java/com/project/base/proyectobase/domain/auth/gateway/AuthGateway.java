package com.project.base.proyectobase.domain.auth.gateway;

import com.project.base.proyectobase.domain.auth.User;

public interface AuthGateway {
    User registrarUsuario(User user);
    User consultarUsuarioPorUsername(String username);
}
