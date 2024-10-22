package co.edu.unicauca.mvc.vistas.articlePanels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.utilities.CustomTextField;
import co.edu.unicauca.mvc.utilities.Components;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.vistas.authorPanels.ListAuthorsPanel;
import co.edu.unicauca.mvc.vistas.authorPanels.RegisterAuthorPanel;
import co.edu.unicauca.mvc.vistas.genericPanels.RegisterPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.windows.PopUpWindow;

import static javax.swing.SwingUtilities.getWindowAncestor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RegisterArticlePanel extends RegisterPanel{
    private final CardPanelManager cardManager;
    private final int conferenceID;
    private  StorageService<Author> tempAuthors;

    public RegisterArticlePanel(CardPanelManager cardManager, int conferenceID) {
        super(new JLabel("Registrar Articulo"), createInputFields());
        this.conferenceID = conferenceID;
        this.cardManager = cardManager;
    }

    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre: ", new FieldConfig(new CustomTextField("Nombre: ")));
        inputFields.put("Revista: ", new FieldConfig(new CustomTextField("Revista: ")));
        inputFields.put("Abstract: ", new FieldConfig(new CustomTextField("Abstract: ")));
        inputFields.put("Palabras clave: ", new FieldConfig(new CustomTextField("Palabras clave: ")));
        inputFields.put("", new FieldConfig(new JButton("Asignar autor")));
        return inputFields;
    }

    @Override
    protected void registerAction() {
        if(!Components.valuesAreCorrect(fieldConfigs)){
            new PopUpWindow((JFrame) getWindowAncestor(this), 
            PopUpWindow.PopUpType.ERROR, 
            "Debe llenar todos los campos");
            return;
        }
        ArrayList<String> values = Components.extractTextFields(fieldConfigs);
        Article article = new Article(values.get(0), values.get(1), values.get(2), values.get(3));
        if(tempAuthors == null ||  tempAuthors.listAll().isEmpty()){
            new PopUpWindow((JFrame) getWindowAncestor(this), 
            PopUpWindow.PopUpType.ERROR, 
            "Debe asignar al menos un autor");
            return;
        }
        GeneralRepository.getConferenceLinkServiceById(conferenceID).storeArticles(article.getId());
        GeneralRepository.storeArticle(article);
        for (Author author : tempAuthors.listAll()) {
            GeneralRepository.getArticleLinkServiceById(article.getId()).
                    storeAuthors(author.getId());
            GeneralRepository.storeAuthor(author);
        }
        cardManager.showPanel("listPanel");
        new PopUpWindow((JFrame) getWindowAncestor(this), 
            PopUpWindow.PopUpType.SUCCESS, 
            "Articulo registrado con exito");
        cleanInputs();
    }

    @Override
    protected void extraButtonAction() {
         this.tempAuthors = new StorageService<>(new MemoryArrayListRepository<>());
        ListAuthorsPanel listAuthorsPanel = new ListAuthorsPanel(cardManager, tempAuthors); 
        RegisterAuthorPanel registerAuthorPanel = new RegisterAuthorPanel(cardManager, tempAuthors); 
        
        tempAuthors.addObserver((Observer) listAuthorsPanel);
        ((Observer) listAuthorsPanel).update();
        
        cardManager.addPanel(listAuthorsPanel,"listAuthorPanel");
        cardManager.addPanel(registerAuthorPanel,"registerAuthorPanel");
        cardManager.showPanel("listAuthorPanel");

    }

}
