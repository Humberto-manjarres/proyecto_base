package com.project.base.proyectobase.domain.auth.gateway;

import com.project.base.proyectobase.domain.auth.User;

import java.util.Map;

public interface JwtGateway {
    String generarToken(User user, Map<String,Object> extraClaims);
}
