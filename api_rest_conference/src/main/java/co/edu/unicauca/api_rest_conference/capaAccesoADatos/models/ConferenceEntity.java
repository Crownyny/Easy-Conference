package co.edu.unicauca.api_rest_conference.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ConferenceEntity {
    private int id; /*Id de la conferencia */
    private int adminId; /*Id del usuario que creo la conferencia */
    private String name; /*Nombre de la conferencia */
    private Date startDate; /*Fecha de inicio de la conferencia */
    private Date endDate; /*Fecha de fin de la conferencia */
    private float registrationCost; /*Costo de la inscripción a la conferencia */
    private String location; /*Ubicación de la conferencia */
    private List<String> topics;  /*Temas de la conferencia */
    private List<Integer> articles; /*Artículos asociados a la conferencia */
    private List<Integer> organizers; /*Organizadores de la conferencia */
    public ConferenceEntity() {
    }   
    public void storeOrganizers(int... organizerIds) {
        for (int organizerId : organizerIds) {
            organizers.add(organizerId);
        }
    }

    public void storeArticles(int... articleIds) {
        for (int articleId : articleIds) {
            articles.add(articleId);
        }
    }
}
