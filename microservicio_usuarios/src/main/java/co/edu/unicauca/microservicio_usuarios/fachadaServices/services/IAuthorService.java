package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import java.util.List;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.AuthorDTO;

public interface IAuthorService {
    /** 
     * Este metodo retorna todos los autores
     * @return Lista de autores
     */
    public List<AuthorDTO> findAll();

    /**
     * Este metodo busca un autor por su identificador
     * @param id Identificador del autor
     * @return Autor
     */
    public AuthorDTO findById(int id);

    /**
     * Este metodo guarda un autor
     * @param author Autor a guardar
     * @return Autor ingresado
     */
    public AuthorDTO store(AuthorDTO author);

    /**
     * Este metodo guarda una lista de autores
     * @param authors Lista de autores
     * @return Autores ingresados
     */
    // public void store(AuthorDTO ... authors);

    /**
     * Este metodo actualiza un autor
     * @param id Identificador del autor
     * @param author Autor a actualizar
     * @return Autor actualizado
     */
    public AuthorDTO update(int id, AuthorDTO author);

    /**
     * Este metodo elimina un autor
     * @param id Identificador del autor
     * @return Autor eliminado
     */
    public boolean delete(int id);
    
}