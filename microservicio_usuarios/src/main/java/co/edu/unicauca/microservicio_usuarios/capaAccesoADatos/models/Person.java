package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Person implements InterfaceIdentifiable {
    protected String firstName;
    protected String lastName;
    protected String mail;
    protected int id;

    public Person() {
    }
}
