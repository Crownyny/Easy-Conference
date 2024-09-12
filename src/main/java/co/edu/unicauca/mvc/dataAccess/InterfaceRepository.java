package co.edu.unicauca.mvc.dataAccess;

import java.util.List;

public interface InterfaceRepository<T> {
    public boolean store(T obj);
    public List<T> listAll();
}
