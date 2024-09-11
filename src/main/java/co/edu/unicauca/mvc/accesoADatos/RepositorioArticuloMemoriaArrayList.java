package co.edu.unicauca.mvc.accesoADatos;

import java.util.ArrayList;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Articulo;


public class RepositorioArticuloMemoriaArrayList implements InterfaceRepositorioArticulo{

    private final ArrayList<Articulo> listaArticulos;
    
    public RepositorioArticuloMemoriaArrayList()
    {
        this.listaArticulos= new ArrayList();
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
