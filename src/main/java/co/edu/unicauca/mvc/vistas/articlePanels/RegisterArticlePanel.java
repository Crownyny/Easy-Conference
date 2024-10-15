package co.edu.unicauca.mvc.vistas.articleRelatedPanels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.utilities.CustomTextField;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.GeneralUtilities;
import co.edu.unicauca.mvc.vistas.authorPanels.ListAuthorsPanel;
import co.edu.unicauca.mvc.vistas.authorPanels.RegisterAuthorPanel;
import co.edu.unicauca.mvc.vistas.genericPanels.RegisterPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JButton;
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
        inputFields.put("", new FieldConfig(new JButton("Asignar autor")));
        return inputFields;
    }

    @Override
    protected void registerAction() {
        if(!Elements.valuesAreCorrect(fieldConfigs)){
            GeneralUtilities.warningMessage("Debe rellenar todos los campos", "Registro fallido");
            return;
        }
        ArrayList<String> values = Elements.extractTextFields(fieldConfigs);
        Article article = new Article(values.get(0), values.get(1));
        if(tempAuthors == null ||  tempAuthors.listAll().isEmpty()){
            GeneralUtilities.warningMessage("Debe asignar por lo menos un autor", "Registro fallido");
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
