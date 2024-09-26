package co.edu.unicauca.mvc.models;

/**
 *
 * @author Default
 */
public class Evaluator extends Person{
    private String afiliation;
    private static int idCont = 0;

    public Evaluator() {
    }

    public Evaluator(String afiliation, String firstName, String lastName, String mail) {
        super(firstName, lastName, mail, ++idCont);
        this.afiliation = afiliation;
    }
    
    

    public String getAfiliation() {
        return afiliation;
    }

    public void setAfiliation(String afiliation) {
        this.afiliation = afiliation;
    } 
    
}
