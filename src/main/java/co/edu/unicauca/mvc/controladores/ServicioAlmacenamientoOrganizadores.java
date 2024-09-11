package co.edu.unicauca.mvc.controladores;

import co.edu.unicauca.mvc.accesoADatos.InterfaceRepositorioOrganizador;
import co.edu.unicauca.mvc.infraestructura.Subject;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Organizador;

public class ServicioAlmacenamientoOrganizadores extends Subject{
    
    private final InterfaceRepositorioOrganizador referenciaRepositorioOrganizadors;
    
    public ServicioAlmacenamientoOrganizadores(InterfaceRepositorioOrganizador referenciaRepositorioOrganizadors)
    {
        this.referenciaRepositorioOrganizadors=referenciaRepositorioOrganizadors;
    }
    
    public boolean almacenarOrganizador(Organizador objConfererencia) {
        boolean bandera=this.referenciaRepositorioOrganizadors.almacenarOrganizador(objConfererencia);
        this.notifyAllObserves();        
        return bandera;
    }

   
    public List<Organizador> listarOrganizadores() {
        return this.referenciaRepositorioOrganizadors.listarOrganizadors();
    }
}
