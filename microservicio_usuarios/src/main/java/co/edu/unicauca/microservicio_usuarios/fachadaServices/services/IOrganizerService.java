package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.OrganizerDTO;
import java.util.List;

public interface IOrganizerService {
    /**
     * Retorna una lista con todos los organizadores
     * @return Lista de organizadores
     */
    public List<OrganizerDTO> findAll();

    /**
     * Busca un organizador por su identificador
     * @param id Identificador del organizador
     * @return Organizador
     */
    public OrganizerDTO findById(int id);

    /**
     * Guarda un organizador
     * @param organizer Organizador a guardar
     * @return Organizador ingresado
     */
    public OrganizerDTO store(OrganizerDTO organizer);

    /**
     * Guarda una lista de organizadores
     * @param organizers Lista de organizadores
     * @return Organizadores ingresados
     */
    // public void store(OrganizerDTO ... organizers);

    /**
     * Actualiza un organizador
     * @param id Identificador del organizador
     * @param organizer Organizador a actualizar
     * @return Organizador actualizado
     */
    public OrganizerDTO update(int id, OrganizerDTO organizer);

    /**
     * Elimina un organizador
     * @param id Identificador del organizador
     * @return Organizador eliminado
     */
    public boolean delete(int id);
}
