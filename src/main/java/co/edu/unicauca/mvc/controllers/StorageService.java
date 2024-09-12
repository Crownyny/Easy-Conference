package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.dataAccess.InterfaceRepository;
import co.edu.unicauca.mvc.infrastructure.Subject;
import java.util.List;

public class StorageService<T> extends Subject{
    
    private final InterfaceRepository repositoryReference;
    
    public StorageService(InterfaceRepository articleRepositoryReference) {
        this.repositoryReference = articleRepositoryReference;
    }
    
    public boolean store(T obj) {
        boolean flag = this.repositoryReference.store(obj);
        this.notifyAllObservers();
        return flag;
    }

    public List<T> listAll() {
        return this.repositoryReference.listAll();
    }  
    
}
