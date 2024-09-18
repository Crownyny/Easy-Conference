package co.edu.unicauca.mvc.dataAccess;

import java.util.LinkedList;
import java.util.List;

public class MemoryLinkedListRepository<T> implements InterfaceRepository<T>{

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
}
