package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.UserDTO;
import java.util.List;

public interface IUserService {
    /**
     * Listar todos los usuarios
     * @return Lista de usuarios
     */
    public List<UserDTO> findAll();

    /**
     * Buscar un usuario por su identificador
     * @param id Identificador del usuario
     * @return Usuario
     */
    public UserDTO findById(int id);

    /**
     * Guarda un usuario
     * @param user Usuario a guardar
     * @return usuario ingresado
     */
    public UserDTO store(UserDTO user);

    /**
     * Guarda una lista de usuarios
     * @param Lista de usuarios
     */
    // public void store(UserDTO ... users);

    /**
     * Actualiza un usuario
     * @param id Identificador del usuario
     * @param user Usuario a actualizar
     * @return Usuario actualizado
     */
    public UserDTO update(int id, UserDTO user);

    /**
     * Elimina un usuario
     * @param id Identificador del usuario
     * @return Usuario eliminado
     */
    public boolean delete(int id);
}
