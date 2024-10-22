package co.edu.unicauca.mvc.models;


public class Article implements InterfaceIdentifiable{
    private String title;
    private String journal;
    private String abstractText;
    private String keywords;
    private int id;
    private static int idCont = 0;
    
    public Article(String title, String Journal, String abstractText, String keywords) {
        this.title = title;
        this.journal = Journal;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.id = idCont++;
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

    public String getAbstract() {
        return abstractText;
    }

    public void setAbstract(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
