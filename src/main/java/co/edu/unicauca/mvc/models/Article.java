package co.edu.unicauca.mvc.models;

import java.util.List;

public class Article {
    private String title;
    private String journal;
    private float authorCount;
    private List<Author> authors;  

    public Article(String title, List<Author> authors, float authorCount, String journal) {
        this.title = title;
        this.journal = journal;
        this.authorCount = authorCount;
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

    public float getAuthorCount() {
        return authorCount;
    }

    public void setAuthorCount(float authorCount) {
        this.authorCount = authorCount;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


}
