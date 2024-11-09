package com.project.base.proyectobase.infrastructure.entry_point.auth.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface UserTransformer {

    AuthResponse tokenToAuthResponse(String token);
}
