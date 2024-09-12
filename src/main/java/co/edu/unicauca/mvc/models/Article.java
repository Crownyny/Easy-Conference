package co.edu.unicauca.mvc.models;

import java.util.List;

public class Article {
    private String title;
    private String journal;
    private List<Author> authors;  

    public Article(String title, List<Author> authors, String journal) {
        this.title = title;
        this.journal = journal;
        this.authors = authors;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


}
