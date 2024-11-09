package com.project.base.proyectobase.infrastructure.entry_point.auth.mapper;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombre;
    private String username;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatedPassword;
}
