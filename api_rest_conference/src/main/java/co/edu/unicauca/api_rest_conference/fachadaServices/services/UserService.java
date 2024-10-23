package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.UserDTO;
import reactor.core.publisher.Mono;

public class UserService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    public Mono<UserDTO> getUserById(Integer idUser){
        String url = "http://localhost:8080/api/user/"+idUser;
        Mono<UserDTO> user = webClientBuilder.build()
            .get().uri(url).retrieve()
            .bodyToMono(UserDTO.class);
        return user;        
    }
}
