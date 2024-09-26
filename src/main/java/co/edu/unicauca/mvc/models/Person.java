package co.edu.unicauca.mvc.models;

public abstract class Person implements InterfaceIdentifiable{
    protected String firstName;
    protected String lastName;
    protected String mail;
    protected int id;
    public Person() {
    }

    public Person(String firstName, String lastName, String mail, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.id = id;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
