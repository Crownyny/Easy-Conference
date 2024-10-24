
package co.edu.unicauca.microservicio_gestion_articulos.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter; 

import java.util.List;

@Getter //genera todos los gets para todos los atributos
@Setter //genera todos los sets para todos los atributos
@AllArgsConstructor //genera todos los constructores para todos los atributos combinados, menos el vacio
public class ArticleEntity {
    private Integer id;
    private Integer idUser;
    private String title;
    private String journal;
    private String abstractText;
    private String keywords;
    private int idCont;//
    private List<Integer> listAuthors;
    private List<Integer> listEvaluators;

    public ArticleEntity(){
    }

    public void storeAuthor(int... idAuthor) {
        for (int authorId : idAuthor) {
            listAuthors.add(authorId);
        }
    }

    public void storeEvaluator(int... idEvaluator) {
        for (int evaluatorId : idEvaluator) {
            listEvaluators.add(evaluatorId);
        }
    }
}

