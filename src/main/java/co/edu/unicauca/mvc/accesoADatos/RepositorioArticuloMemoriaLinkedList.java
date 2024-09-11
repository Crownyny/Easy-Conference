package co.edu.unicauca.mvc.accesoADatos;

import java.util.LinkedList;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Articulo;


public class RepositorioArticuloMemoriaLinkedList implements InterfaceRepositorioArticulo{

    private final LinkedList<Articulo> listaArticulos;
    
    public RepositorioArticuloMemoriaLinkedList()
    {
        this.listaArticulos= new LinkedList();
    }
    
    @Override
    public boolean almacenarArticulo(Articulo objConfererencia) {
        boolean bandera=this.listaArticulos.add(objConfererencia);
        return bandera;
    }

    @Override
    public List<Articulo> listarArticulos() {
        return this.listaArticulos;
    }
    
}
