package co.edu.unicauca.mvc.accesoADatos;

import java.util.List;
import co.edu.unicauca.mvc.modelos.Articulo;

public interface InterfaceRepositorioArticulo {
    public boolean almacenarArticulo(Articulo objArticulo);
    public List<Articulo> listarArticulos();
}
