package co.edu.unicauca.mvc.models;

import java.util.Date;

public class User extends Person{
    private String password;

    public User(String firstName, String lastName, String mail, String password) {
        super(firstName, lastName, mail);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
