package co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*esta clase mapea cliente entity hacia clientedto o viceversa, el mapeo es necesario 
para no exponer la estructura de base de datos*/
@Configuration /*-> esta anotacion va a tener metodos de configuracion */
public class mapper {
    @Bean /*-> toma el obj modelMapper y guardarlo en el contenedor de dependecias */
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}