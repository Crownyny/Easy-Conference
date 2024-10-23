package co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EvaluatorDTO extends PersonDTO{
    private String afiliation;
    private static int idCont = 0;

    public EvaluatorDTO() {
    }

    public EvaluatorDTO(String afiliation, String firstName, String lastName, String mail) {
        super(firstName, lastName, mail, ++idCont);
        this.afiliation = afiliation;
    }
}
