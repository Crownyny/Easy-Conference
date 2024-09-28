package co.edu.unicauca.mvc.vistas.authorPanels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.Utilities;
import co.edu.unicauca.mvc.vistas.genericPanels.RegisterPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterAuthorPanel extends RegisterPanel{
    private final CardPanelManager cardManager; 
    private final StorageService<Author> tempAuthors;
    
    public RegisterAuthorPanel(CardPanelManager cardManager,StorageService<Author> tempAuthors) {
        super(new JLabel("Registrar Autor"), createInputFields());
        this.tempAuthors = tempAuthors;
        this.cardManager = cardManager;
    }
    
    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre:", new FieldConfig(new JTextField(20)));
        inputFields.put("Apellido:", new FieldConfig(new JTextField(20)));
        inputFields.put("Mail:", new FieldConfig(new JTextField(30)));
        return inputFields;
    }
    
         
    @Override
    protected void registerAction() {
        ArrayList<String> values = Elements.extractTextFields(fieldConfigs);
        
        try{
            Author author = new Author(values.get(0),values.get(1), values.get(2));
            System.out.println("Deberia llamar a update");
            tempAuthors.store(author);
            cleanInputs();
            cardManager.showPanel("listAuthorPanel");
        } catch (NumberFormatException ex) {
            Utilities.warningMessage("El id debe ser numérico", "Formato de costo inválido");
        }
    }

}
