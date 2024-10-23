package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User extends Person {
    private String password;
    private static int idCont = 0;

    public User() {
    }

    public User(String mail, String password, String firstName, String lastName) {
        super(firstName, lastName, mail, ++idCont);
        this.password = password;
    }
}
