package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ArticleDTO;
import reactor.core.publisher.Mono;

@Service
public class ArticleService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    public Mono<ArticleDTO> getArticleById(Integer idArticle){
        String url = "http://localhost:5000/api/articles/"+idArticle;
        Mono<ArticleDTO> art = webClientBuilder.build()
            .get().uri(url).retrieve()
            .bodyToMono(ArticleDTO.class);
        return art;        
    }

    
}
