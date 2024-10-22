package co.edu.unicauca.mvc.vistas.evaluatorPanel;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Evaluator;
import co.edu.unicauca.mvc.utilities.CustomTextField;
import co.edu.unicauca.mvc.utilities.Components;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.vistas.genericPanels.RegisterPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.windows.PopUpWindow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.SwingUtilities.getWindowAncestor;

public class RegisterEvaluatorPanel extends RegisterPanel{
    private final CardPanelManager cardManager;
    private final int articleID;

    public RegisterEvaluatorPanel(CardPanelManager cardManager, int articleID) {
        super(new JLabel("Registrar Evaluador"), createInputFields());
        this.articleID = articleID;
        this.cardManager =cardManager;
    }

    
    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre: ", new FieldConfig(new CustomTextField("Nombre: ")));
        inputFields.put("Apellido: ", new FieldConfig(new CustomTextField("Apellido: ")));
        inputFields.put("Mail: ", new FieldConfig(new CustomTextField("Mail: ")));
        inputFields.put("Afiliation: ", new FieldConfig(new CustomTextField("Afiliation: ")));
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
        
        Evaluator evaluator = new Evaluator(values.get(0),values.get(1), values.get(2), values.get(3));
        GeneralRepository.getArticleLinkServiceById(articleID).storeEvaluators(evaluator.getId());
        GeneralRepository.storeEvaluator(evaluator);
        cleanInputs();
        cardManager.showPanel("listEvaluatorPanel");   
           
        new PopUpWindow((JFrame) getWindowAncestor(this),
        PopUpWindow.PopUpType.SUCCESS,
        "Evaluador registrado exitosamente");    
        
    }

}
