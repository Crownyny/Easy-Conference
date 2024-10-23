package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import java.util.List;


import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ArticleDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ConferenceDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.OrganizerDTO;


public interface IConferenceService {
	/**
	 * Buscar todas las conferencias
	 * @return Lista de conferencias
	 */
    public List<ConferenceDTO> findAll();
	/**
	 * Buscar una conferencia por su id
	 * @param id Id de la conferencia
	 * @return Conferencia
	 */
	public ConferenceDTO findById(Integer id);
	/**
	 * Guardar una conferencia
	 * @param conferencia Conferencia a guardar
	 * @return Conferencia guardada
	 */
	public ConferenceDTO save(ConferenceDTO conferencia);
	/**
	 * Actualizar una conferencia
	 * @param id Id de la conferencia
	 * @param conferencia Conferencia a actualizar
	 * @return Conferencia actualizada
	 */
	public ConferenceDTO update(Integer id, ConferenceDTO conferencia);
	/**
	 * Contar la cantidad de articulos en una conferencia
	 * @param id Id de la conferencia
	 * @return Cantidad de articulos
	 */
	public int countArticlesInConference(Integer id);
	/**
	 * Eliminar una conferencia
	 * @param id Id de la conferencia
	 * @return True si se elimino, false en caso contrario
	 */
	public boolean delete(Integer id);
	/**
	 * Verificar si una conferencia existe
	 * @param id Id de la conferencia
	 * @return True si existe, false en caso contrario
	 */
    public boolean exists(int id);
	/**
	 * Obtener los articulos de una conferencia
	 * @param idConference Id de la conferencia
	 * @return Lista de articulos
	 */
	public List<ArticleDTO> getArticlesByConferenceId(int idConference);
	/**
	 * Obtener los organizadores de una conferencia
	 * @param idConference Id de la conferencia
	 * @return Lista de organizadores
	 */
	public List<OrganizerDTO> getOrganizersByConferenceId(int idConference);
	/**
	 * Obtener las conferencias de un usuario
	 * @param userId Id del usuario
	 * @return Lista de conferencias
	 */
	public List<ConferenceDTO> getConferencesByUserId(int userId);
}
