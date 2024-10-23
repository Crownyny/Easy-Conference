package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Evaluator extends Person {
    private String afiliation;
    private static int idCont = 0;

    public Evaluator() {
    }

    public Evaluator(String afiliation, String firstName, String lastName, String mail) {
        super(firstName, lastName, mail, ++idCont);
        this.afiliation = afiliation;
    }
}
