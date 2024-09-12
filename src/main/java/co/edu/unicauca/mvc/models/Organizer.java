package co.edu.unicauca.mvc.modelos;

/**
 *
 * @author Default
 */
public class Organizador {
    private String nombres;
    private String appellidos;
    private String universidad;

    public Organizador(String nombres, String appellidos, String universidad) {
        this.nombres = nombres;
        this.appellidos = appellidos;
        this.universidad = universidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAppellidos() {
        return appellidos;
    }

    public void setAppellidos(String appellidos) {
        this.appellidos = appellidos;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    
    
}
