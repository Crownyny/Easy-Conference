package co.edu.unicauca.mvc.dataAccess;

import co.edu.unicauca.mvc.models.InterfaceIdentifiable;
import java.util.ArrayList;
import java.util.List;

public class MemoryArrayListRepository<T extends InterfaceIdentifiable> implements InterfaceRepository<T>{

    protected final ArrayList<T> list;

    public MemoryArrayListRepository() {
        this.list = new ArrayList<>();
    }
    
    public MemoryArrayListRepository(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public boolean store(T obj) {
        return this.list.add(obj);
    }

    @Override
    public List<T> listAll() {
        return this.list;
    }

    @Override
    public T getById(int id) {
        return list.stream()
           .filter(obj -> obj.getId() == id)  
           .findFirst()
           .orElse(null);
    }
}
