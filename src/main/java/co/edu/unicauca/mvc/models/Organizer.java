package co.edu.unicauca.mvc.models;

public class Organizer  extends Person{
    private String university;
    private static int idCont = 0;

    public Organizer() {
    }

    public Organizer(String firstName, String lastName, String mail,String university) {
        super(firstName, lastName, mail, ++idCont);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
