package co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//esta clase encapsula datos que viajan de articulo al servidor
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
