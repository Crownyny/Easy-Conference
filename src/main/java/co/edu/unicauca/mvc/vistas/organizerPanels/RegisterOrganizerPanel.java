package co.edu.unicauca.mvc.vistas.organizerPanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Organizer;
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

public class RegisterOrganizerPanel extends RegisterPanel{
    private final CardPanelManager cardManager;
    private final int conferenceID;

    public RegisterOrganizerPanel(CardPanelManager cardManager, int conferenceID) {
        super(new JLabel("Registrar Organizador"), createInputFields());
        this.cardManager = cardManager;
        this.conferenceID = conferenceID;
    }

    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombres: ", new FieldConfig(new CustomTextField("Nombre: ")));
        inputFields.put("Apellidos: ", new FieldConfig(new CustomTextField("Apellidos: ")));
        inputFields.put("Mail: ", new FieldConfig(new CustomTextField("Mail: ")));
        inputFields.put("Universidad: ", new FieldConfig(new CustomTextField("Universidad: ")));
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

        Organizer organizer = new Organizer(values.get(0), values.get(1), values.get(2), values.get(3));
        GeneralRepository.getConferenceLinkServiceById(conferenceID).storeOrganizers(organizer.getId());
        GeneralRepository.storeOrganizer(organizer);
        cleanInputs();
        
        cardManager.showPanel("listPanel");
        new PopUpWindow((JFrame) getWindowAncestor(this),
        PopUpWindow.PopUpType.SUCCESS,
        "Organizador registrado exitosamente");
    }

}
