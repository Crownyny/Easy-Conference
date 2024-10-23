package co.edu.unicauca.api_rest_conference.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ArticleDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.ConferenceDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.DTO.UserDTO;
import co.edu.unicauca.api_rest_conference.fachadaServices.services.IConferenceService;

@RestController
@RequestMapping("/api")
public class ConferenceRestController {
    //Aqui se inyecta la dependencia de la interfaz IConferenceService
    @Autowired
    private IConferenceService conferenceService;

    //El primer servicio REST recibe una conferencia y la guarda en la base de datos. Retorna la conferencia guardada.
    @PostMapping("/conferences")
	public ConferenceDTO createConference(@RequestBody ConferenceDTO conference) {
		ConferenceDTO objConference = conferenceService.save(conference);
		return objConference;
	}
    //El segundo servicio REST recibe el ID de la conferencia y retorna la conferencia que corresponde con el ID.
    @GetMapping("/conferences/{id}")
    public ConferenceDTO getConferenceById(@PathVariable Integer id) {
        ConferenceDTO conf = conferenceService.findById(id);
        return conf;
    }
    //El tercer servicio REST no recibe datos y retorna una lista de las conferencias guardadas.
    @GetMapping("/conferences/list")
    public List<ConferenceDTO> listConferences() {
        return conferenceService.findAll();
    }
    //El cuarto servicio REST recibe el ID de la conferencia a actualizar y los nuevos datos de ella , y retorna la
    //conferencia actualizada
    @PutMapping("/conferences/{id}")
    public ConferenceDTO updateConference(@PathVariable Integer id, @RequestBody ConferenceDTO newConference) {
        ConferenceDTO oldConference = conferenceService.findById(id);
        ConferenceDTO updatedConference = null;
        if(oldConference!=null){
            updatedConference = conferenceService.update(id, newConference);
        }
        return updatedConference;
    }
    //El quinto servicio REST recibe el ID de la conferencia y retorna la cantidad de artículos que tiene asociados.
    @GetMapping("conferences/countArticles/{id}")
    public int countArticlesInConference(@PathVariable Integer id) {
        return this.conferenceService.countArticlesInConference(id);
    }
    
    //El sexto servicio REST recibe el ID de la conferencia a eliminar y retorna true o false si se eliminó.
    @DeleteMapping("/conferences/{id}")
    public boolean deleteConference(@PathVariable Integer id){
        boolean bandera = false;
        ConferenceDTO conf = conferenceService.findById(id);
        if(conf!=null) bandera = conferenceService.delete(id);
        return bandera;
    }
    // El septimo servicio REST recibe el ID de la conferencia a consultar y retorna true o false si existe.     
    @GetMapping("/conferences/exists")
    public boolean verifyExistenceConference(@RequestParam Integer id) {
        return conferenceService.exists(id);
    }
    @GetMapping("/conferences/getArticles/{idConference}")
    public List<ArticleDTO> getArticlesByConference(@PathVariable Integer idConference) {
        return conferenceService.getArticlesByConference(idConference);
    }
    @GetMapping("/conferences/getOrganizers/{idConference}")
    public List<UserDTO> getOrganizerByConference(@PathVariable Integer idConference) {
        return conferenceService.getOrganizerByConference(idConference);
    }
    
}
