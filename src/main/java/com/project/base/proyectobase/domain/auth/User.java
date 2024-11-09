package com.project.base.proyectobase.domain.auth;

import com.project.base.proyectobase.domain.auth.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String nombre;
    private String username;//email
    private String password;
    private String repeatedPassword;
    private Role role;
}
