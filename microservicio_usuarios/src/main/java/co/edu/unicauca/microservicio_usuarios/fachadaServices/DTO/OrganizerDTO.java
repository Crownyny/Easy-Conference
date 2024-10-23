package co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrganizerDTO extends PersonDTO {
    private String university;
    private static int idCont = 0;

    public OrganizerDTO() {
    }

    public OrganizerDTO(String firstName, String lastName, String mail, String university) {
        super(firstName, lastName, mail, ++idCont);
        this.university = university;
    }
}
