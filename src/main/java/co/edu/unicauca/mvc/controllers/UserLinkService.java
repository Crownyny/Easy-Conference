package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.models.InterfaceIdentifiable;
import java.util.ArrayList;
import java.util.List;

public class UserLinkService implements InterfaceIdentifiable{
    private final int userId;
    private final List<Integer> linkedConferences;
    private final List<Integer> linkedArticles;
    
    public UserLinkService(int userId) {
        this.userId = userId;
        this.linkedConferences = new ArrayList<>();
        this.linkedArticles = new ArrayList<>();
    }

    public int getUser() {
        return userId;
    }

    public List<Integer> getConferences() {
        return linkedConferences;
    }

    public List<Integer> getArticles() {
        return linkedArticles;
    }

    public void storeConferences(int ... conferences) {
        for (int conference : conferences) {
            linkedConferences.add(conference);
        }
    }
    
    public void storeArticles(int ... articles) {
        for (int article : articles) {
            linkedArticles.add(article);
        }
    }

    @Override
    public int getId() {
        return userId;
    }
}
