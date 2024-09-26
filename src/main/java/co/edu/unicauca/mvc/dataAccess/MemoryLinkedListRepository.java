package co.edu.unicauca.mvc.dataAccess;

import co.edu.unicauca.mvc.models.InterfaceIdentifiable;
import java.util.LinkedList;
import java.util.List;

public class MemoryLinkedListRepository<T extends InterfaceIdentifiable> implements InterfaceRepository<T>{

    protected final LinkedList<T> list;

    public MemoryLinkedListRepository() {
        this.list = new LinkedList<>();
    }
    
    public MemoryLinkedListRepository(LinkedList<T> list) {
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
