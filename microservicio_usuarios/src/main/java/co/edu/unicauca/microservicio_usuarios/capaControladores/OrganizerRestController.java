package co.edu.unicauca.microservicio_usuarios.capaControladores;

import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.services.IOrganizerService;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.OrganizerDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class OrganizerRestController {
    // Inyectar el servicio de organizadores
    @Autowired
    private IOrganizerService organizerService;

    // Este servicio REST lista todos los organizadores
    @GetMapping("/organizers")
    public List<OrganizerDTO> getAllOrganizers() {
        List<OrganizerDTO> listOrganizers = organizerService.findAll();
        return listOrganizers;
    }

    // Este servicio REST busca un organizador por su id
    @GetMapping("/organizers/{id}")
    public OrganizerDTO getOrganizerById(@PathVariable int id) {
        OrganizerDTO organizer = organizerService.findById(id);
        return organizer;
    }

    // Este servicio REST guarda un organizador
    @PostMapping("/organizers")
    public OrganizerDTO saveOrganizer(@RequestBody OrganizerDTO organizer) {
        OrganizerDTO organizerStored = organizerService.store(organizer);
        return organizerStored;
    }

    // Este servicio REST guarda una lista de organizadores
    // @PostMapping("/organizers2")
    // public void saveOrganizers(@RequestBody OrganizerDTO ... organizers) {
    // organizerService.store(organizers);
    // }

    // Este servicio REST actualiza un organizador
    @PutMapping("/organizers/{id}")
    public OrganizerDTO updateOrganizer(@PathVariable int id, @RequestBody OrganizerDTO organizer) {
        OrganizerDTO organizerUpdated = organizerService.update(id, organizer);
        return organizerUpdated;
    }

    // Este servicio REST elimina un organizador
    @DeleteMapping("/organizers/{id}")
    public boolean deleteOrganizer(@PathVariable int id) {
        return organizerService.delete(id);
    }

}
