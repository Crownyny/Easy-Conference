package co.edu.unicauca.api_rest_conference.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConferenceDTO {
    private int id;
    private String name;
    private int maxArticlesCuantity;
    public ConferenceDTO() {
    }  
}
