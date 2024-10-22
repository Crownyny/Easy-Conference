package co.edu.unicauca.mvc.vistas.authorPanels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.utilities.CustomTextField;
import co.edu.unicauca.mvc.utilities.Components;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.vistas.genericPanels.RegisterPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.windows.PopUpWindow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.SwingUtilities.getWindowAncestor;

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
        inputFields.put("Nombre: ", new FieldConfig(new CustomTextField("Nombre: ")));
        inputFields.put("Apellido: ", new FieldConfig(new CustomTextField("Apellido: ")));
        inputFields.put("Mail: ", new FieldConfig(new CustomTextField("Mail: ")));
        JComboBox<String> comboBoxTipoAutor = new JComboBox<>(new String[] {"Tipo Asociado", "Tipo Independiente"});
        inputFields.put("Tipo de autor:", new FieldConfig(comboBoxTipoAutor));

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
        
        try{
            FieldConfig comboBoxFieldConfig = fieldConfigs.get("Tipo de autor:");
            JComboBox<String> comboBoxTipoAutor = (JComboBox<String>) comboBoxFieldConfig.getFieldType();
            String typeAuthor = (String) comboBoxTipoAutor.getSelectedItem();
            Author author = new Author(values.get(0),values.get(1), values.get(2), typeAuthor);
            tempAuthors.store(author);
            cleanInputs();
            cardManager.showPanel("listAuthorPanel");
            new PopUpWindow((JFrame) getWindowAncestor(this),
            PopUpWindow.PopUpType.SUCCESS,
            "Autor registrado exitosamente");
        } catch (NumberFormatException ex) {
            new PopUpWindow((JFrame) getWindowAncestor(this),
            PopUpWindow.PopUpType.WARNING,
            "El costo de inscripción debe ser un número");
        }
    }

}
