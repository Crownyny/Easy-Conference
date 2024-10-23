package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Author extends Person {
    private String typeAuthor;
    private static int idCont = 0;

    public Author() {
    }

    public Author(String firstName, String lastName, String mail, String typeAuthor) {
        super(firstName, lastName, mail, ++idCont);
        this.typeAuthor = typeAuthor;
    }
}
