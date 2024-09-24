package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.dataAccess.InterfaceRepository;
import co.edu.unicauca.mvc.models.User;
import java.util.List;

public class UserManagementService {
    private final User user;
    private final StorageService<ConferenceManagementService> conferenceService;
    private final StorageService<ArticleManagementService> articleService;
    
    public UserManagementService(User user,
            InterfaceRepository<ConferenceManagementService> conferenceRepository, 
            InterfaceRepository<ArticleManagementService> articleRepository) {
        this.user = user;
        this.conferenceService = new StorageService<>(conferenceRepository);
        this.articleService = new StorageService<>(articleRepository);
    }

    public User getUser() {
        return user;
    }

    public StorageService<ConferenceManagementService> getConferenceService() {
        return conferenceService;
    }

    public StorageService<ArticleManagementService> getArticleService() {
        return articleService;
    }
    
    public void storeOrganizer(ConferenceManagementService ... conferences)
    {
        for (ConferenceManagementService conference : conferences)
             conferenceService.store(conference);
    }
    
    public void storeArticle(ArticleManagementService ... articles)
    {
        for (ArticleManagementService article : articles)
            articleService.store(article);
    }

    public List<ConferenceManagementService> listConferences()
    {
        return conferenceService.listAll();
    }       
    
        public List<ArticleManagementService> listArticles()
    {
        return articleService.listAll();
    }      
}
