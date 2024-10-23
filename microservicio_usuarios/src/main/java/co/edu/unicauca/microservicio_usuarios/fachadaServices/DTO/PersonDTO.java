package co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class PersonDTO implements InterfaceIdentifiableDTO {
    protected String firstName;
    protected String lastName;
    protected String mail;
    protected int id;

    public PersonDTO() {
    }
}
