package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.dataAccess.InterfaceRepository;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.models.Evaluator;
import java.util.List;

public class ArticleManagementService {
    private final Article article;
    private final StorageService<Author> authorService;
    private final StorageService<Evaluator> evaluatorService;

    public ArticleManagementService(Article article, InterfaceRepository<Author> repositoryAuthor, InterfaceRepository<Evaluator> repositoryEvaluator) {
        this.article = article;
        this.authorService = new StorageService<>(repositoryAuthor);
        this.evaluatorService = new StorageService<>(repositoryEvaluator);
    }

    public ArticleManagementService(Article article, StorageService<Author> authorService, StorageService<Evaluator> evaluatorService) {
        this.article = article;
        this.authorService = authorService;
        this.evaluatorService = evaluatorService;
    }
    

    public void storeAuthor(Author... authors) {
        for (Author author : authors) {
            authorService.store(author);
        }
    }

    public List<Author> listAuthors() {
        return authorService.listAll();
    }

    public Article getArticle() {
        return article;
    }

    public StorageService<Author> getAuthorService() {
        return authorService;
    }
    
    public List<Evaluator> listEvaluators() {
        return evaluatorService.listAll();
    }

    public StorageService<Evaluator> getEvaluatorService() {
        return evaluatorService;
    }    
}
