package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Organizer extends Person {
    private String university;
    private static int idCont = 0;

    public Organizer() {
    }

    public Organizer(String firstName, String lastName, String mail, String university) {
        super(firstName, lastName, mail, ++idCont);
        this.university = university;
    }
    
}
