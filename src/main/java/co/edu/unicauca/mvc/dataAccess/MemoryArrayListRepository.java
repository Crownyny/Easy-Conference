package co.edu.unicauca.mvc.dataAccess;

import java.util.ArrayList;
import java.util.List;

public class MemoryArrayListRepository<T> implements InterfaceRepository<T>{

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
}
