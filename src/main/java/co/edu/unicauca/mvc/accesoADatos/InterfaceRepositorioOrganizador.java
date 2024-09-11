package co.edu.unicauca.mvc.accesoADatos;

import java.util.List;
import co.edu.unicauca.mvc.modelos.Organizador;

public interface InterfaceRepositorioOrganizador {
    public boolean almacenarOrganizador(Organizador objOrganizador);
    public List<Organizador> listarOrganizadors();
}
