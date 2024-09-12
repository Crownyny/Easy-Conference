package co.edu.unicauca.mvc.modelos;


/**
 *
 * @author Default
 */
public class Articulo {
    private String nombre;
    private String revista;
    private float cantidadAutores;
    private String autores;  

    public Articulo(String nombre, String autores, float cantidadAutores, String revista) {
        this.nombre = nombre;
        this.revista = revista;
        this.cantidadAutores = cantidadAutores;
        this.autores = autores;
    }  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public float getCantidadAutores() {
        return cantidadAutores;
    }

    public void setCantidadAutores(float cantidadAutores) {
        this.cantidadAutores = cantidadAutores;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    
}
