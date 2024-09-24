package co.edu.unicauca.mvc.models;

public class Author extends Person{
    private float id;

    public Author(String firstName, String lastName, String email, float id) {
        super(firstName, lastName, email);
        this.id = id;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }  

}
