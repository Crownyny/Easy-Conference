package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.OrganizerDTO;
import reactor.core.publisher.Mono;
@Service
public class UserService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    public Mono<OrganizerDTO> getUserById(Integer idUser){
        String url = "http://localhost:8080/api/user/"+idUser;
        Mono<OrganizerDTO> user = webClientBuilder.build()
            .get().uri(url).retrieve()
            .bodyToMono(OrganizerDTO.class);
        return user;        
    }
}
