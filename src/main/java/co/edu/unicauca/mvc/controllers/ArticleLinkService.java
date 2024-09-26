package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.models.InterfaceIdentifiable;
import java.util.ArrayList;
import java.util.List;

public class ArticleLinkService implements InterfaceIdentifiable{
    private final int articleId;
    private final List<Integer> linkedAuthors;    // IDs of authors
    private final List<Integer> linkedEvaluators; // IDs of evaluators

    public ArticleLinkService(int articleId) {
        this.articleId = articleId;
        this.linkedAuthors = new ArrayList<>();
        this.linkedEvaluators = new ArrayList<>();
    }

    public int getArticle() {
        return articleId;
    }

    public List<Integer> getAuthors() {
        return linkedAuthors;
    }

    public List<Integer> getEvaluators() {
        return linkedEvaluators;
    }

    public void storeAuthors(int... authorIds) {
        for (int authorId : authorIds) {
            linkedAuthors.add(authorId);
        }
    }

    public void storeEvaluators(int... evaluatorIds) {
        for (int evaluatorId : evaluatorIds) {
            linkedEvaluators.add(evaluatorId);
        }
    }

    public List<Integer> listAuthors() {
        return linkedAuthors;
    }

    public List<Integer> listEvaluators() {
        return linkedEvaluators;
    }

    @Override
    public int getId() {
        return articleId;
    }
}
