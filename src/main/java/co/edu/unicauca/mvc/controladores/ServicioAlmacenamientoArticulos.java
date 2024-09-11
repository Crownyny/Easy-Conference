package co.edu.unicauca.mvc.controladores;

import  co.edu.unicauca.mvc.accesoADatos.InterfaceRepositorioArticulo;
import co.edu.unicauca.mvc.infraestructura.Subject;
import java.util.List;
import  co.edu.unicauca.mvc.modelos.Articulo;


public class ServicioAlmacenamientoArticulos extends Subject{
    
    private final InterfaceRepositorioArticulo referenciaRepositorioArticulos;
    
    public ServicioAlmacenamientoArticulos(InterfaceRepositorioArticulo referenciaRepositorioArticulos)
    {
        this.referenciaRepositorioArticulos=referenciaRepositorioArticulos;
    }
    
    public boolean almacenarArticulo(Articulo objConfererencia) {
        boolean bandera=this.referenciaRepositorioArticulos.almacenarArticulo(objConfererencia);
        this.notifyAllObserves();
        return bandera;
    }

   
    public List<Articulo> listarArticulos() {
        return this.referenciaRepositorioArticulos.listarArticulos();
    }
}
