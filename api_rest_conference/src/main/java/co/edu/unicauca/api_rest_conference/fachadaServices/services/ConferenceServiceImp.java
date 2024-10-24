package co.edu.unicauca.api_rest_conference.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import co.edu.unicauca.api_rest_conference.capaAccesoADatos.models.ConferenceEntity;
import co.edu.unicauca.api_rest_conference.capaAccesoADatos.repositories.ConferenceRepository;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ArticleDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ConferenceDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.OrganizerDTO;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImp implements IConferenceService{
    private ConferenceRepository servicioAccesoBaseDatos;
    private ArticleService servicioArticulos;
    private UserService servicioUsuarios;
    private ModelMapper modelMapper;
    public ConferenceServiceImp(ConferenceRepository servicioBaseDatos, ModelMapper modelMapper, ArticleService servicioArticulos, UserService servicioUsuarios){ 
        this.servicioAccesoBaseDatos = servicioBaseDatos;
        this.modelMapper = modelMapper;
        this.servicioArticulos = servicioArticulos;
        this.servicioUsuarios = servicioUsuarios;
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
    public List<ArticleDTO> getArticlesByConferenceId(int idConference) {
        // Obtenemos la conferencia por ID
        ConferenceDTO conference = this.findById(idConference);
        //Obtenemos los Ids de los artículos asociados a la conferencia
        List<Integer> articleIds = conference.getArticles();
        // Para cada ID de artículo, hacemos una solicitud al microservicio de artículos
        Flux<ArticleDTO> articles = Flux.fromIterable(articleIds)
            .flatMap(this.servicioArticulos::getArticleById); // Llamamos a un método que busca el artículo por ID
        
        // Convertimos el Flux a una lista de artículos
        return articles.collectList().block(); // .block() para ejecutar de manera sincrónica
    }
    @Override
    public List<OrganizerDTO> getOrganizersByConferenceId(int idConference) {
        // Obtenemos la conferencia por ID
        ConferenceDTO conference = this.findById(idConference);
        //Obtenemos los Ids de los organizadores asociados a la conferencia
        List<Integer> organizerIds = conference.getOrganizers();
        Flux<OrganizerDTO> organizers = Flux.fromIterable(organizerIds)
            .flatMap(this.servicioUsuarios::getUserById); // Llamamos a un método que busca el usuario por ID
        
        // Convertimos el Flux a una lista de artículos
        return organizers.collectList().block(); // .block() para ejecutar de manera sincrónica
    }
    @Override
    public List<ConferenceDTO> getConferencesByUserId(int userId) {
        List<ConferenceEntity> conferencias = this.servicioAccesoBaseDatos.getConferencesByUserId(userId);
        List<ConferenceDTO> conferenciasDTO = this.modelMapper.map(conferencias, new TypeToken<List<ConferenceDTO>>() {
		}.getType());
        return conferenciasDTO;
    }
}
