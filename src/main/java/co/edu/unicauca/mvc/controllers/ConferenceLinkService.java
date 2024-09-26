package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.models.InterfaceIdentifiable;
import java.util.ArrayList;
import java.util.List;

public class ConferenceLinkService implements InterfaceIdentifiable{
    private final int conferenceId;
    private final List<Integer> linkedOrganizers;  // IDs of organizers
    private final List<Integer> linkedArticles;    // IDs of articles
    
    public ConferenceLinkService(int conferenceId) {
        this.conferenceId = conferenceId;
        this.linkedOrganizers = new ArrayList<>();
        this.linkedArticles = new ArrayList<>();
    }

    public int getConference() {
        return conferenceId;
    }

    public List<Integer> getOrganizers() {
        return linkedOrganizers;
    }

    public List<Integer> getArticles() {
        return linkedArticles;
    }

    public void storeOrganizers(int... organizerIds) {
        for (int organizerId : organizerIds) {
            linkedOrganizers.add(organizerId);
        }
    }

    public void storeArticles(int... articleIds) {
        for (int articleId : articleIds) {
            linkedArticles.add(articleId);
        }
    }

    @Override
    public int getId() {
        return conferenceId;
    }
}
