package com.project.base.proyectobase.infrastructure.entry_point.auth;

import com.project.base.proyectobase.domain.auth.User;
import com.project.base.proyectobase.domain.usecase.auth.UserUseCase;
import com.project.base.proyectobase.infrastructure.entry_point.auth.mapper.AuthResponse;
import com.project.base.proyectobase.infrastructure.entry_point.auth.mapper.AuthenticationRequest;
import com.project.base.proyectobase.infrastructure.entry_point.auth.mapper.RegisterRequest;
import com.project.base.proyectobase.infrastructure.entry_point.auth.mapper.UserTransformer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserUseCase userUseCase;

    private final UserTransformer transformer;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request){
        AuthResponse authResponse = transformer.tokenToAuthResponse(userUseCase.registrarUsuario(
                User.builder().nombre(request.getNombre()).username(request.getUsername())
                        .password(request.getPassword()).repeatedPassword(request.getRepeatedPassword()).build()));
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthenticationRequest auth){
        AuthResponse authResponse = transformer.tokenToAuthResponse(
                userUseCase.loginUsuario(User.builder().username(auth.getUsername()).password(auth.getPassword()).build()));
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/user-logged")
    public ResponseEntity<User> consultarUsuarioLogueado(){
        return ResponseEntity.ok(userUseCase.consultarUsuarioLogueado());
    }
}
