package co.edu.unicauca.api_rest_conference.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class PersonDTO implements InterfaceIdentifiable{
    protected String firstName;
    protected String lastName;
    protected String mail;
    protected int id;
    public PersonDTO() {
    }
    
}
