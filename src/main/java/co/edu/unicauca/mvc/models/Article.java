package co.edu.unicauca.mvc.models;


public class Article {
    private String title;
    private String journal;

    public Article(String title, String journal) {
        this.title = title;
        this.journal = journal;
    }  

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}
