package co.edu.unicauca.mvc.accesoADatos;

import java.util.ArrayList;
import java.util.List;
import co.edu.unicauca.mvc.modelos.Organizador;


public class RepositorioOrganizadorMemoriaArrayList implements InterfaceRepositorioOrganizador{

    private final ArrayList<Organizador> listaOrganizadors;
    
    public RepositorioOrganizadorMemoriaArrayList()
    {
        this.listaOrganizadors= new ArrayList();
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
