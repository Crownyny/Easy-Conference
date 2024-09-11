package co.edu.unicauca.mvc.accesoADatos;

import java.util.LinkedList;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Organizador;


public class RepositorioOrganizadorMemoriaLinkedList implements InterfaceRepositorioOrganizador{

    private final LinkedList<Organizador> listaOrganizadors;
    
    public RepositorioOrganizadorMemoriaLinkedList()
    {
        this.listaOrganizadors= new LinkedList();
    }
    
    @Override
    public boolean almacenarOrganizador(Organizador objConfererencia) {
        boolean bandera=this.listaOrganizadors.add(objConfererencia);
        return bandera;
    }

    @Override
    public List<Organizador> listarOrganizadors() {
        return this.listaOrganizadors;
    }
    
}
