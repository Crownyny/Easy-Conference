package co.edu.unicauca.api_rest_conference.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Arrays;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.api_rest_conference.capaAccesoADatos.models.ConferenceEntity;

@Repository
public class ConferenceRepository {
    private ArrayList<ConferenceEntity> listConferences; //Aqui se almacenaran las conferencias
    private AtomicInteger idIterator;
    public ConferenceRepository(){
        this.listConferences = new ArrayList<ConferenceEntity>();
        //loadConferences();
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
    /*private void loadConferences(){
        ConferenceEntity conference1 = new ConferenceEntity();
        conference1.setId(1);
        conference1.setAdminId(101);
        conference1.setName("Tech Innovations 2024");
        conference1.setStartDate(new Date(2024, 2, 18));
        conference1.setEndDate(new Date(2024, 2, 20));
        conference1.setRegistrationCost(250.50f);
        conference1.setLocation("San Francisco, USA");
        conference1.setTopics(Arrays.asList("AI", "Blockchain", "Cybersecurity"));
        conference1.setArticles(Arrays.asList(1001, 1002, 1003));
        conference1.setOrganizers(Arrays.asList(201, 202, 203));
        ConferenceEntity conference2 = new ConferenceEntity();
        conference2.setId(2);
        conference2.setAdminId(102);
        conference2.setName("Global Healthcare Summit 2024");
        conference2.setStartDate(new Date(2024, 5, 10));
        conference2.setEndDate(new Date(2024, 5, 12));
        conference2.setRegistrationCost(300.00f);
        conference2.setLocation("London, UK");
        conference2.setTopics(Arrays.asList("Telemedicine", "Genomics", "Medical Devices"));
        conference2.setArticles(Arrays.asList(1002, 1003));
        conference2.setOrganizers(Arrays.asList(301, 302));
        ConferenceEntity conference3 = new ConferenceEntity();
        conference3.setId(3);
        conference3.setAdminId(103);
        conference3.setName("Sustainability and Energy 2024");
        conference3.setStartDate(new Date(2024, 9, 1));
        conference3.setEndDate(new Date(2024, 9, 5));
        conference3.setRegistrationCost(150.75f);
        conference3.setLocation("Tokyo, Japan");
        conference3.setTopics(Arrays.asList("Renewable Energy", "Environmental Policies", "Green Technology"));
        conference3.setArticles(Arrays.asList(1001,1003));
        conference3.setOrganizers(Arrays.asList(401, 402, 403));
        listConferences.add(conference1);
        listConferences.add(conference2);
        listConferences.add(conference3);
    }*/
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
    /* This could be used in a future version
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

    }*/ 
    public List<ConferenceEntity> getConferencesByUserId(int userId){
        List<ConferenceEntity> conferences = new ArrayList<ConferenceEntity>();
        for(ConferenceEntity conf : listConferences){
            if(conf.getAdminId()==userId){
                conferences.add(conf);
            }
        }
        return conferences;
    }
}
