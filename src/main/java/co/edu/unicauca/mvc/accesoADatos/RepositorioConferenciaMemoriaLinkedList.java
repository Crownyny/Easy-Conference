package co.edu.unicauca.mvc.accesoADatos;

import java.util.LinkedList;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Conferencia;

/**
 *
 * @author HSVSTT2
 */
public class RepositorioConferenciaMemoriaLinkedList implements InterfaceRepositorioConferencia
{
    private final LinkedList<Conferencia> listaConferencias;
    
    public RepositorioConferenciaMemoriaLinkedList()
    {
        this.listaConferencias= new LinkedList();
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
