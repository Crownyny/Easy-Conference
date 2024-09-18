package co.edu.unicauca.mvc.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conference {
    private String name;
    private Date startDate;
    private Date endDate;
    private float registrationCost;
    private String location;
    private List<String> topics;
    private List<Organizer> organizers;
    private List<Article> articles;

    public Conference(String name, Date startDate, Date endDate, float registrationCost, String location, List<String> topics) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationCost = registrationCost;
        this.location = location;
        this.topics = topics;
        this.organizers = new ArrayList<>();
        this.articles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getRegistrationCost() {
        return registrationCost;
    }

    public void setRegistrationCost(float registrationCost) {
        this.registrationCost = registrationCost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    
    public String topicsToString() {
    if (topics.isEmpty()) 
        return ""; 
    
    return String.join(", ", topics);
    }

    public List<Organizer> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<Organizer> organizers) {
        this.organizers = organizers;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
