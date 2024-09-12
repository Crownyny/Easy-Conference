package co.edu.unicauca.mvc.models;

public class Organizer {
    private String firstName;
    private String lastName;
    private String university;

    public Organizer(String firstName, String lastName, String university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.university = university;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
