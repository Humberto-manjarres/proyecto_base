package com.project.base.proyectobase.domain.usecase.auth;

import com.project.base.proyectobase.domain.auth.User;
import com.project.base.proyectobase.domain.auth.gateway.AuthGateway;
import com.project.base.proyectobase.domain.auth.gateway.JwtGateway;
import com.project.base.proyectobase.domain.auth.util.Role;
import com.project.base.proyectobase.domain.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class UserUseCase {

    private final AuthGateway authGateway;

    private final JwtGateway jwtGateway;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public String registrarUsuario(User user){
        this.validarSiExisteUsernameEnBD(authGateway.consultarUsuarioPorUsername(user.getUsername()));
        this.validatePassword(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User userRegistrado = this.authGateway.registrarUsuario(user);
        return jwtGateway.generarToken(user, this.generateExtraClaims(userRegistrado));
    }

    public String loginUsuario(User user){
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        this.authenticationManager.authenticate(authentication);//verificar que el usuario sea quien dice ser con el username y password.
        User userBD = authGateway.consultarUsuarioPorUsername(user.getUsername());//buscamos en BD por username.
        String jwt = jwtGateway.generarToken(userBD, generateExtraClaims(userBD));//generación del token.
        //this.saveUserToken((User) userBD,jwt);//guardar token en BD para cuando hagamos logout
        return jwt;
    }

    public User consultarUsuarioLogueado(){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        User user = authGateway.consultarUsuarioPorUsername(username);
        this.validarSiExisteUser(user);
        return user;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getNombre());
        extraClaims.put("role",user.getRole());
        extraClaims.put("authorities",user.getRole().getPermissions());
        return extraClaims;
    }

    private void validarSiExisteUser(User user){
        if (Objects.isNull(user)) throw new BusinessException(BusinessException.Type.USUARIO_NO_EXISTE);
    }

    private void validarSiExisteUsernameEnBD(User user){
        if (Objects.nonNull(user)) throw new BusinessException(BusinessException.Type.USERNAME_YA_EXISTE);
    }

    private void validatePassword(User user) {
        //valida si el campo password o repeatedPassword no tienen texto
        if (!StringUtils.hasText(user.getPassword()) || !StringUtils.hasText(user.getRepeatedPassword())){
            throw new BusinessException(BusinessException.Type.PASSWORD_INVALIDO);
        }

        if (!user.getPassword().equals(user.getRepeatedPassword())){
            throw new BusinessException(BusinessException.Type.PASSWORD_NO_COINCIDEN);
        }
    }

}
