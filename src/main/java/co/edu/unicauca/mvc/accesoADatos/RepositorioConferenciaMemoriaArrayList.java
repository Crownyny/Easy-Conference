package co.edu.unicauca.mvc.accesoADatos;

import java.util.ArrayList;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Conferencia;


public class RepositorioConferenciaMemoriaArrayList implements InterfaceRepositorioConferencia{

    private final ArrayList<Conferencia> listaConferencias;
    
    public RepositorioConferenciaMemoriaArrayList()
    {
        this.listaConferencias= new ArrayList();
    }
    
    @Override
    public boolean almacenarConferencia(Conferencia objConfererencia) {
        boolean bandera=this.listaConferencias.add(objConfererencia);
        return bandera;
    }

    @Override
    public List<Conferencia> listarConferencias() {
        return this.listaConferencias;
    }
    
}
