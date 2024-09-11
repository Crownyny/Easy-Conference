package co.edu.unicauca.mvc.accesoADatos;

import java.util.List;
import co.edu.unicauca.mvc.modelos.Conferencia;

public interface InterfaceRepositorioConferencia {
    public boolean almacenarConferencia(Conferencia objConferencia);
    public List<Conferencia> listarConferencias();
}
