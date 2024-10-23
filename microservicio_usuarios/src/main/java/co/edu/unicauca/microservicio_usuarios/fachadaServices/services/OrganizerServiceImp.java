package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.Organizer;
import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories.OrganizerRepository;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.OrganizerDTO;

@Service
public class OrganizerServiceImp implements IOrganizerService {
    private OrganizerRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public OrganizerServiceImp(OrganizerRepository servicioAccesoBaseDatos, ModelMapper mapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = mapper;
    }

    @Override
    public List<OrganizerDTO> findAll() {
        System.out.println("Retornando todos los organizadores");
        List<Organizer> listOrganizers = servicioAccesoBaseDatos.getAllOrganizers();
        List<OrganizerDTO> listOrganizersDTO = this.modelMapper.map(listOrganizers, new TypeToken<List<OrganizerDTO>>() {
        }.getType());
        return listOrganizersDTO;
    }

    @Override
    public OrganizerDTO findById(int id) {
        System.out.println("Buscando organizador por id");
        Organizer organizer = servicioAccesoBaseDatos.getOrganizerById(id);
        OrganizerDTO organizerDTO = null;
        if(organizer != null){
            organizerDTO = this.modelMapper.map(organizer, OrganizerDTO.class);
        }
        return organizerDTO;
    }

    @Override
    public OrganizerDTO store(OrganizerDTO organizer) {
        System.out.println("Guardando organizador");
        Organizer organizerEntity = this.modelMapper.map(organizer, Organizer.class);
        Organizer organizerStored = servicioAccesoBaseDatos.store(organizerEntity);
        OrganizerDTO organizerDTO = this.modelMapper.map(organizerStored, OrganizerDTO.class);
        return organizerDTO;
    }

    // @Override
    // public void store(OrganizerDTO ... organizers) {
    // System.out.println("Guardando lista de organizadores");
    //  for(OrganizerDTO organizer : organizers){
    //  Organizer organizerEntity = this.modelMapper.map(organizer, Organizer.class);
    //   servicioAccesoBaseDatos.store(organizerEntity);
    //  }
    // }

    @Override
    public OrganizerDTO update(int id, OrganizerDTO organizer) {
        System.out.println("Actualizando organizador");
        Organizer organizerEntity = this.modelMapper.map(organizer, Organizer.class);
        organizerEntity.setId(id);
        Organizer organizerUpdated = servicioAccesoBaseDatos.update(organizerEntity);
        OrganizerDTO organizerDTO = this.modelMapper.map(organizerUpdated, OrganizerDTO.class);
        return organizerDTO;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("Eliminando organizador");
        return servicioAccesoBaseDatos.delete(id);
    }
    
}
