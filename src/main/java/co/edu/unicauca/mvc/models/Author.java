package co.edu.unicauca.mvc.models;

public class Author extends Person{
    private static int idCont = 0;
    private String typeAuthor;
    
    public Author(String firstName, String lastName, String mail, String typeAuthor) {
        super(firstName, lastName, mail, ++idCont);
        this.typeAuthor = typeAuthor;
    }
    
    public void setTypeAuthor(String typeAuthor) {
        this.typeAuthor = typeAuthor;
    }
    
    public String getTypeAuthor(){
        return this.typeAuthor;
    }
}
