package co.edu.unicauca.mvc.infrastructure;

import java.util.ArrayList;

public abstract class Subject {

    /**
     * List of observers
     */
    protected final ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Empty Constructor
     */
    public void Subject() {}

    /**
     * Adds an observer to the list of observers.
     * 
     * If the list of observers is null, it initializes it.
     * The observer is then added to the list.
     *
     * @param obs The observer to be added
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * Notifies all registered observers that the model has changed.
     * 
     * This method iterates over all the observers in the list and calls their
     * update method, passing the current instance (the subject) as the argument.
     */
    public void notifyAllObservers() {
        for (Observer each : observers) {
            each.update();
        }
    }



}
