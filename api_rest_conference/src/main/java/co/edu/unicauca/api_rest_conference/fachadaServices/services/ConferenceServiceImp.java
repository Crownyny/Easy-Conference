package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import co.edu.unicauca.api_rest_conference.capaAccesoADatos.models.ConferenceEntity;
import co.edu.unicauca.api_rest_conference.capaAccesoADatos.repositories.ConferenceRepository;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ConferenceDTO;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImp implements IConferenceService{
    private ConferenceRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;
    public ConferenceServiceImp(ConferenceRepository servicioBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioBaseDatos;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ConferenceDTO> findAll() {
        List<ConferenceEntity> conferencias = servicioAccesoBaseDatos.findAll();
        List<ConferenceDTO> conferenciasDTO = this.modelMapper.map(conferencias, new TypeToken<List<ConferenceDTO>>() {
		}.getType());
        return conferenciasDTO;
    }

    @Override
    public ConferenceDTO findById(Integer id) {
        ConferenceEntity objConferenceEntity = this.servicioAccesoBaseDatos.findById(id);
		ConferenceDTO conferenceDTO = null;
		if(objConferenceEntity!=null) {
			conferenceDTO = this.modelMapper.map(objConferenceEntity, ConferenceDTO.class);
		}
		return conferenceDTO;
    }

    @Override
    public ConferenceDTO save(ConferenceDTO conferencia) {
        ConferenceEntity objConferenceEntity = this.modelMapper.map(conferencia, ConferenceEntity.class);
        ConferenceEntity objConferenceEntitySaved = this.servicioAccesoBaseDatos.save(objConferenceEntity);
        ConferenceDTO conferenceDTO = this.modelMapper.map(objConferenceEntitySaved, ConferenceDTO.class);
        return conferenceDTO;
    }

    @Override
    public ConferenceDTO update(Integer id, ConferenceDTO conferencia) {
        ConferenceEntity objConferenceEntity = this.modelMapper.map(conferencia, ConferenceEntity.class);
        ConferenceEntity objConferenceEntitySaved = this.servicioAccesoBaseDatos.update(id,objConferenceEntity);
        ConferenceDTO conferenceDTO = this.modelMapper.map(objConferenceEntitySaved, ConferenceDTO.class);
        return conferenceDTO;
    }
    @Override
    public int countArticlesInConference(Integer id) {
        return this.servicioAccesoBaseDatos.countArticlesInConference(id);
    }

    @Override
    public boolean delete(Integer id) {
        return this.servicioAccesoBaseDatos.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return this.servicioAccesoBaseDatos.exists(id);
    }
    @Override
    public List<ConferenceDTO> getConferencesByArticle(int idArticle) {
        List<ConferenceEntity> conferencias = servicioAccesoBaseDatos.getConferencesByArticle(idArticle);
        List<ConferenceDTO> conferenciasDTO = this.modelMapper.map(conferencias, new TypeToken<List<ConferenceDTO>>() {
            }.getType());
        return conferenciasDTO;
    }

}
