package co.edu.unicauca.mvc.controllers;

import co.edu.unicauca.mvc.dataAccess.InterfaceRepository;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Conference;
import co.edu.unicauca.mvc.models.Organizer;
import java.util.List;

public class ConferenceManagementService {
    private final Conference conference;
    private final StorageService<Organizer> organizerService;
    private final StorageService<ArticleManagementService> articleService;

    public ConferenceManagementService(Conference conference,InterfaceRepository<Organizer> organzizerRepository, InterfaceRepository<Article> articleRepository) {
        this.conference = conference;
        this.organizerService = new StorageService<>(organzizerRepository);
        this.articleService = new StorageService<>(articleRepository);
    }

    public StorageService<Organizer> getOrganizerService() {
        return organizerService;
    }

    public StorageService<ArticleManagementService> getArticleService() {
        return articleService;
    }
    
    public void storeOrganizer(Organizer ... organizers)
    {
        for (Organizer organizer : organizers)
             organizerService.store(organizer);
    }
    
    public void storeOrganizer(ArticleManagementService ... articles)
    {
        for (ArticleManagementService article : articles)
            articleService.store(article);
    }

    public Conference getConference() {
        return conference;
    }
    
    public List<Organizer> listOrganizers()
    {
        return organizerService.listAll();
    }       
    
        public List<ArticleManagementService> listArticles()
    {
        return articleService.listAll();
    }       
}
