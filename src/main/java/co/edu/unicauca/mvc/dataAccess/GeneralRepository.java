package co.edu.unicauca.mvc.dataAccess;

import co.edu.unicauca.mvc.controllers.*;
import co.edu.unicauca.mvc.infrastructure.Subject;
import co.edu.unicauca.mvc.models.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralRepository extends Subject{
    private static StorageService<Article> articleService;
    private static StorageService<Author> authorService;
    private static StorageService<Conference> conferenceService;
    private static StorageService<Evaluator> evaluatorService;
    private static StorageService<Organizer> organizerService;
    private static StorageService<User> userService;
    private static StorageService<UserLinkService> userLinkService;
    private static StorageService<ConferenceLinkService> conferenceLinkService;
    private static StorageService<ArticleLinkService> articleLinkService;

    public static void initializeManagementService() {
        MemoryArrayListRepository<Article> articleRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<Author> authorRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<Conference> conferenceRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<Evaluator> evaluatorRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<Organizer> organizerRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<User> userRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<UserLinkService> userLinkRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<ConferenceLinkService> conferenceLinkRepository = new MemoryArrayListRepository<>();
        MemoryArrayListRepository<ArticleLinkService> articleLinkRepository = new MemoryArrayListRepository<>();

        articleService = new StorageService<>(articleRepository);
        authorService = new StorageService<>(authorRepository);
        conferenceService = new StorageService<>(conferenceRepository);
        evaluatorService = new StorageService<>(evaluatorRepository);
        organizerService = new StorageService<>(organizerRepository);
        userService = new StorageService<>(userRepository);
        userLinkService = new StorageService<>(userLinkRepository);
        conferenceLinkService = new StorageService<>(conferenceLinkRepository);
        articleLinkService = new StorageService<>(articleLinkRepository);
    }

    // Métodos getById para cada tipo de dato
    public static Article getArticleById(int id) {
        return articleService.getById(id);
    }

    public static Author getAuthorById(int id) {
        return authorService.getById(id);
    }

    public static Conference getConferenceById(int id) {
        return conferenceService.getById(id);
    }

    public static Evaluator getEvaluatorById(int id) {
        return evaluatorService.getById(id);
    }

    public static Organizer getOrganizerById(int id) {
        return organizerService.getById(id);
    }

    public static User getUserById(int id) {
        return userService.getById(id);
    }

    public static UserLinkService getUserLinkServiceById(int id) {
        return userLinkService.getById(id);
    }

    public static ConferenceLinkService getConferenceLinkServiceById(int id) {
        return conferenceLinkService.getById(id);
    }

    public static ArticleLinkService getArticleLinkServiceById(int id) {
        return articleLinkService.getById(id);
    }

    // Métodos store para cada tipo de dato
    public static void storeArticle(Article... articles) {
        for (Article article : articles) {
            articleLinkService.store(new ArticleLinkService(article.getId()));
            articleService.store(article);
        }
    }

    public static void storeAuthor(Author... authors) {
        for (Author author : authors) {
            authorService.store(author);
            articleService.notifyAllObservers();
        }
    }

    public static void storeConference(Conference... conferences) {
        for (Conference conference : conferences) {
            conferenceLinkService.store(new ConferenceLinkService(conference.getId()));
            conferenceService.store(conference);
        }
    }

    public static void storeEvaluator(Evaluator... evaluators) {
        for (Evaluator evaluator : evaluators) {
            evaluatorService.store(evaluator);
        }
    }

    public static void storeOrganizer(Organizer... organizers) {
        for (Organizer organizer : organizers) {
            organizerService.store(organizer);
            System.out.println("Meti datos " + organizerService.listAll().size());
        }
    }

    public static void storeUser(User... users) {
        for (User user : users) {
            userLinkService.store(new UserLinkService(user.getId()));
            userService.store(user);
        }
    }
    
    // Getters

    public static StorageService<Article> getArticleService() {
        return articleService;
    }

    public static StorageService<Author> getAuthorService() {
        return authorService;
    }

    public static StorageService<Conference> getConferenceService() {
        return conferenceService;
    }

    public static StorageService<Evaluator> getEvaluatorService() {
        return evaluatorService;
    }

    public static StorageService<Organizer> getOrganizerService() {
        return organizerService;
    }

    public static StorageService<User> getUserService() {
        return userService;
    }

    public static StorageService<UserLinkService> getUserLinkService() {
        return userLinkService;
    }

    public static StorageService<ConferenceLinkService> getConferenceLinkService() {
        return conferenceLinkService;
    }

    public static StorageService<ArticleLinkService> getArticleLinkService() {
        return articleLinkService;
    }
    
    public static List<Author> getAuthorsByArticleId(int articleId) {
        ArticleLinkService linkService = getArticleLinkServiceById(articleId);
        List<Author> authors = new ArrayList<>();
        if (linkService != null) {
            for (int authorId : linkService.getAuthors()) {
                authors.add(getAuthorById(authorId));
            }
        }
        return authors;
    }

    public static List<Evaluator> getEvaluatorsByArticleId(int articleId) {
        ArticleLinkService linkService = getArticleLinkServiceById(articleId);
        List<Evaluator> evaluators = new ArrayList<>();
        if (linkService != null) {
            for (int evaluatorId : linkService.getEvaluators()) {
                evaluators.add(getEvaluatorById(evaluatorId));
            }
        }
        return evaluators;
    }

    // Métodos para obtener objetos relacionados en ConferenceLinkService
    public static List<Organizer> getOrganizersByConferenceId(int conferenceId) {
        ConferenceLinkService linkService = getConferenceLinkServiceById(conferenceId);
        List<Organizer> organizers = new ArrayList<>();
        if (linkService != null) {
            for (int organizerId : linkService.getOrganizers()) {
                organizers.add(getOrganizerById(organizerId));
            }
        }
        return organizers;
    }

    public static List<Article> getArticlesByConferenceId(int conferenceId) {
        ConferenceLinkService linkService = getConferenceLinkServiceById(conferenceId);
        List<Article> articles = new ArrayList<>();
        if (linkService != null) {
            for (int articleId : linkService.getArticles()) {
                articles.add(getArticleById(articleId));
            }
        }
        return articles;
    }

    // Métodos para obtener objetos relacionados en UserLinkService
    public static List<Conference> getConferencesByUserId(int userId) {
        UserLinkService linkService = getUserLinkServiceById(userId);
        List<Conference> conferences = new ArrayList<>();
        if (linkService != null) {
            for (int conferenceId : linkService.getConferences()) {
                conferences.add(getConferenceById(conferenceId));
            }
        }
        return conferences;
    }

    public static List<Article> getArticlesByUserId(int userId) {
        UserLinkService linkService = getUserLinkServiceById(userId);
        List<Article> articles = new ArrayList<>();
        if (linkService != null) {
            for (int articleId : linkService.getArticles()) {
                articles.add(getArticleById(articleId));
            }
        }
        return articles;
    }
    

}

