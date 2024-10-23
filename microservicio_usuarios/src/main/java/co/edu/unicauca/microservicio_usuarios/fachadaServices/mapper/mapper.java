package co.edu.unicauca.microservicio_usuarios.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}