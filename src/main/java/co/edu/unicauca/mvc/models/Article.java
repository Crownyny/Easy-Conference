package co.edu.unicauca.mvc.models;


public class Article implements InterfaceIdentifiable{
    private String title;
    private String journal;
    private int id;
    private static int idCont = 0;
    
    public Article(String title, String journal) {
        this.title = title;
        this.journal = journal;
        this.id = ++idCont;
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
    
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
