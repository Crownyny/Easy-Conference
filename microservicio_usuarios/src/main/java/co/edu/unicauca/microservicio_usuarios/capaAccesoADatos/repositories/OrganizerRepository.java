package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.Organizer;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class OrganizerRepository {
    private ArrayList<Organizer> listOrganizers;

    public OrganizerRepository() {
        this.listOrganizers = new ArrayList<>();
    }

    public ArrayList<Organizer> getAllOrganizers() {
        return listOrganizers;
    }

    public Organizer getOrganizerById(int id) {
        Organizer organizer = null;
        for (Organizer o : listOrganizers) {
            if (o.getId() == id) {
                organizer = o;
                break;
            }
        }
        return organizer;
    }

    public Organizer store(Organizer organizer) {
        Organizer organizador = null;
        if(listOrganizers.add(organizer)) {
            organizador = organizer;
        }
        return organizador;
    }

    /*public void store(Organizer ... organizers) {
        for(Organizer organizer : organizers){
            listOrganizers.add(organizer);
        }
    }*/

    public Organizer update(Organizer organizer) {
        Organizer organizador = null;
        for (Organizer o : listOrganizers) {
            if (o.getId() == organizer.getId()) {
                o.setFirstName(organizer.getFirstName());
                o.setLastName(organizer.getLastName());
                o.setMail(organizer.getMail());
                o.setUniversity(organizer.getUniversity());
                organizador = o;
                break;
            }
        }
        return organizador;
    }

    public boolean delete(int id) {
        boolean result = false;
        for (Organizer o : listOrganizers) {
            if (o.getId() == id) {
                listOrganizers.remove(o);
                result = true;
                break;
            }
        }
        return result;
    }
}
