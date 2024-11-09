package com.project.base.proyectobase.application;

import com.project.base.proyectobase.domain.auth.gateway.AuthGateway;
import com.project.base.proyectobase.domain.auth.gateway.JwtGateway;
import com.project.base.proyectobase.domain.model.empleado.gateway.EmpleadoGateway;
import com.project.base.proyectobase.domain.usecase.auth.UserUseCase;
import com.project.base.proyectobase.domain.usecase.empleado.EmpleadoUseCase;
import com.project.base.proyectobase.domain.usecase.upload.UploadUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigUseCase {

    @Bean
    public EmpleadoUseCase empleadoUseCase(EmpleadoGateway empleadoGateway){
        return new EmpleadoUseCase(empleadoGateway);
    }

    @Bean
    public UserUseCase userUseCase(AuthGateway authGateway, JwtGateway jwtGateway, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        return new UserUseCase(authGateway,jwtGateway,passwordEncoder,authenticationManager);
    }

    @Bean
    public UploadUseCase uploadUseCase(EmpleadoGateway empleadoGateway){
        return new UploadUseCase(empleadoGateway);
    }
}
