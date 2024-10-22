package co.edu.unicauca.api_rest_conference.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.api_rest_conference.capaAccesoADatos.models.ArticleEntity;
import co.edu.unicauca.api_rest_conference.capaAccesoADatos.models.ConferenceEntity;

@Repository
public class ConferenceRepository {
    private ArrayList<ConferenceEntity> listConferences; //Aqui se almacenaran las conferencias
    private AtomicInteger idIterator;
    public ConferenceRepository(){
        this.listConferences = new ArrayList<ConferenceEntity>();
        loadConferences();
        idIterator = new AtomicInteger(listConferences.size());
    }
    public ArrayList<ConferenceEntity> findAll(){ //Recupera todas las conferencias guardadas
        System.out.println("Getting all the conferences");
        return this.listConferences;
    }
    public ConferenceEntity findById(int id){
        System.out.println("Looking for a conference");
        ConferenceEntity conference = null;
        for(ConferenceEntity conf : listConferences){
            if(conf.getId()==id){
                conference = conf;
                break;
            }
        }
        return conference;
    }
    public ConferenceEntity save(ConferenceEntity conf){
        System.out.println("Saving a conference");
        conf.setId(idIterator.incrementAndGet());
        ConferenceEntity conference = null;
        if(listConferences.add(conf)){
            conference = conf;
        }
        return conference;

    }
    public ConferenceEntity update(int id, ConferenceEntity newConference){
        System.out.println("Updating a conference");
        ConferenceEntity oldConference = findById(id);
        if(oldConference!=null){
            newConference.setId(solveId(id,newConference.getId()));
            int index = findIndex(id);
            listConferences.set(index, newConference);
            return newConference;
        }
        return null;
    }
    public int countArticlesInConference(int id){
        System.out.println("Counting articles in a conference");
        ConferenceEntity conference = findById(id);
        return conference.getArticles().size();
    }
    //Este metodo encuentra el indice de la conferencia en la lista, puesto que el id no necesariamente
    //es el indice.
    private int findIndex(int id) {
        for(int i = 0; i < listConferences.size();i++){
            if(listConferences.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    private int solveId(int id, int newId){
        if(id==newId){
            return id;
        }
        for(ConferenceEntity conf : listConferences){
            if(conf.getId()==newId){
                return solveId(id,newId+1);
            }
        }
        return newId;
    }
    private void loadConferences(){
        List<ArticleEntity> articles1 = new ArrayList<ArticleEntity>();
        List<ArticleEntity> articles2 = new ArrayList<ArticleEntity>();
        articles1.add(new ArticleEntity(1));   
        articles1.add(new ArticleEntity(2)); 
        articles2.add(new ArticleEntity(1));
        ConferenceEntity conf1 = new ConferenceEntity(1,"ICSE",10,articles1);        
        ConferenceEntity conf2 = new ConferenceEntity(2,"FSE",10,articles1);
        ConferenceEntity conf3 = new ConferenceEntity(3,"ASE",10,null);
        ConferenceEntity conf4 = new ConferenceEntity(4,"ISSTA",10,articles2);
        ConferenceEntity conf5 = new ConferenceEntity(5,"ICPC",10,articles2);
        listConferences.add(conf1);
        listConferences.add(conf2);
        listConferences.add(conf3);
        listConferences.add(conf4);
        listConferences.add(conf5);
    }
    public boolean delete(Integer id) {
        System.out.println("Deleting a conference");
        boolean bandera=false;
        int index = findIndex(id);
        if(index != -1) {
            listConferences.remove(index);
            return true;
        }
        return bandera;
    }
    public boolean exists(int id) {
        System.out.println("Checking if a conference exists");
        ConferenceEntity conference = findById(id);
        return conference!=null;
    }
    public List<ConferenceEntity> getConferencesByArticle(int idArticle) {
        System.out.println("Getting conferences by article");
        List<ConferenceEntity> conferences = new ArrayList<ConferenceEntity>();
        for(ConferenceEntity conf : listConferences){
            if(conf.getArticles()!=null){
                for(ArticleEntity article : conf.getArticles()){
                    if(article.getId()==idArticle){
                        conferences.add(conf);
                        break;
                    }
                }
            }
        }
        System.out.println("Returning conferences by article, cuantity: "+conferences.size());
        return conferences;

    }
}
