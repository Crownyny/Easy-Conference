package co.edu.unicauca.mvc.models;

/**
 *
 * @author Default
 */
public class Evaluator extends Person{
    private String afiliation;

    public Evaluator() {
    }

    public Evaluator(String firstName, String lastName, String mail, String afiliation) {
        super(firstName, lastName, mail);
        this.afiliation = afiliation;
    }

    public String getAfiliation() {
        return afiliation;
    }

    public void setAfiliation(String afiliation) {
        this.afiliation = afiliation;
    } 
    
}
