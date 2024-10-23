package co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO extends PersonDTO {
    private String typeAuthor;
    private static int idCont = 0;

    public AuthorDTO() {
    }

    public AuthorDTO(String firstName, String lastName, String mail, String typeAuthor) {
        super(firstName, lastName, mail, ++idCont);
        this.typeAuthor = typeAuthor;
    }
}
