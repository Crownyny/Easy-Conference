package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ConferenceDTO;

public interface IConferenceService {
    public List<ConferenceDTO> findAll();

	public ConferenceDTO findById(Integer id);

	public ConferenceDTO save(ConferenceDTO conferencia);

	public ConferenceDTO update(Integer id, ConferenceDTO conferencia);

	public int countArticlesInConference(Integer id);

	public boolean delete(Integer id);

    public boolean exists(int id);

	public List<ConferenceDTO> getConferencesByArticle(int idArticle);
}
