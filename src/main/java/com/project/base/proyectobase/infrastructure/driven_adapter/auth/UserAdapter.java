package com.project.base.proyectobase.infrastructure.driven_adapter.auth;

import com.project.base.proyectobase.domain.auth.User;
import com.project.base.proyectobase.domain.auth.gateway.AuthGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements AuthGateway {

    private final UserRepository repository;

    @Override
    public User registrarUsuario(User user) {
        return getUser(repository.save(getUserEntity(user)));
    }

    @Override
    public User consultarUsuarioPorUsername(String username) {
        return repository.findUserEntityByUsername(username)
                .map(entity -> User.builder()
                        .username(entity.getUsername())
                        .nombre(entity.getNombre())
                        .role(entity.getRole())
                        .build())
                .orElse(null);
    }


    private UserEntity getUserEntity(User user){
        return UserEntity.builder()
                .nombre(user.getNombre())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    private User getUser(UserEntity entity){
        return User.builder()
                .username(entity.getUsername())
                .nombre(entity.getNombre())
                .role(entity.getRole())
                .build();
    }

}
