package co.edu.unicauca.mvc.models;

public class Author extends Person{

    private static int idCont = 0;
    public Author(String firstName, String lastName, String mail) {
        super(firstName, lastName, mail, ++idCont);
    }

}
