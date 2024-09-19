package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.dataAccess.InterfaceRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.infrastructure.Subject;
import java.util.List;

public class StorageService<T> extends Subject{
    
    private final InterfaceRepository repositoryReference;
    
    public StorageService(InterfaceRepository repositoryConference, Observer ...observers) {
        this.repositoryReference = repositoryConference;
        for(Observer observer : observers)
            this.addObserver(observer);
    }
    
    public boolean store(T obj) {
        boolean flag = repositoryReference.store(obj);
        if(!observers.isEmpty())
            notifyAllObservers();
        return flag;
    }

    public List<T> listAll() {
        return repositoryReference.listAll();
    }  
    
}
