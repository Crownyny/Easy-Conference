/**
 * Esta clase sirve para la comunicacion sincrona con el microservicio de gestion de usuarios
 * autor y evaluador
 */
package co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.services;

import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.AuthorDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.EvaluatorDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<AuthorDTO> getAuthorById(Integer idAuthor){
        String url = "http://localhost:8080/api/user/"+idAuthor;
        Mono<AuthorDTO> user = webClientBuilder.build()
            .get().uri(url).retrieve()
            .bodyToMono(AuthorDTO.class);
        return user;        
    }
    
    
    public Mono<EvaluatorDTO> getEvaluatorById(Integer idEvaluator){
        String url = "http://localhost:8080/api/user/"+idEvaluator;
        Mono<EvaluatorDTO> user = webClientBuilder.build()
            .get().uri(url).retrieve()
            .bodyToMono(EvaluatorDTO.class);
        return user;        
    }
    
}
