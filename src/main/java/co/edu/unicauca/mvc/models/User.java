package co.edu.unicauca.mvc.models;

public class User extends Person{
    private String password;
    private static int idCont = 0;

    public User(String mail, String password, String firstName, String lastName ) {
        super(firstName, lastName, mail, ++idCont);
        this.password = password;
    }   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
