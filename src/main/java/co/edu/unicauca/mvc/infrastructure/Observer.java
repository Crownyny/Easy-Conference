package co.edu.unicauca.mvc.infrastructure;
/*
 * Observer interface
 * This interface is implemented by all observers, so they all have the update method
 * This method is called by the subject to notify the observer of any changes
 */
public interface Observer {
    public void update();
}
