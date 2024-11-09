package com.project.base.proyectobase.application;

import com.project.base.proyectobase.infrastructure.driven_adapter.empleado.mapper.EmpleadoAdapterTransformer;
import com.project.base.proyectobase.infrastructure.entry_point.auth.mapper.UserTransformer;
import com.project.base.proyectobase.infrastructure.entry_point.empleado.mapper.EmpleadoTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTransformer {


    @Bean
    public EmpleadoTransformer peliculaTransformer(){
        return Mappers.getMapper(EmpleadoTransformer.class);
    }

    @Bean
    public EmpleadoAdapterTransformer peliculaAdapterTransformer(){
        return Mappers.getMapper(EmpleadoAdapterTransformer.class);
    }

    @Bean
    public UserTransformer userTransformer(){
        return Mappers.getMapper(UserTransformer.class);
    }

}
