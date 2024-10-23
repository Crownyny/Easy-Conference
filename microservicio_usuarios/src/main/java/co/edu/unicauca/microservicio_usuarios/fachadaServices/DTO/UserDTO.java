package co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO extends PersonDTO {
    private String password;
    private static int idCont = 0;

    public UserDTO() {
    }

    public UserDTO(String mail, String password, String firstName, String lastName) {
        super(firstName, lastName, mail, ++idCont);
        this.password = password;
    }
}
