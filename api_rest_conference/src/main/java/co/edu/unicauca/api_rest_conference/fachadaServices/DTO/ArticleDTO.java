package co.edu.unicauca.api_rest_conference.fachadaServices.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ArticleDTO {
    private Integer id;
    private Integer idUser;//id del autor del articulo
    private String title;
    private String journal;
    private String abstractText;
    private String keywords;
    private int idCont;//
    private List<Integer> listAuthors;
    private List<Integer> listEvaluators;
    
    public ArticleDTO(){
            
    }
}
