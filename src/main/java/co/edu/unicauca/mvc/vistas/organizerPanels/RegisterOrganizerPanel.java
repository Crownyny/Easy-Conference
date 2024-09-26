package co.edu.unicauca.mvc.vistas.organizerPanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Organizer;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.vistas.windows.RegisterWindow;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterOrganizerPanel extends RegisterWindow{
    private final int conferenceID;

    public RegisterOrganizerPanel(int conferenceID) {
        super(new JLabel("Registrar Organizador"), createInputFields());
        this.conferenceID = conferenceID;
    }

    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombres:", new FieldConfig(new JTextField(20)));
        inputFields.put("Apellidos:", new FieldConfig(new JTextField(20)));
        inputFields.put("Mail:", new FieldConfig(new JTextField(20)));
        inputFields.put("Universidad:", new FieldConfig(new JTextField(20)));
        return inputFields;
    }
    
    @Override
    protected void registerAction() {
        ArrayList<String> values = Elements.extractTextFields(fieldConfigs);

        Organizer organizer = new Organizer(values.get(0), values.get(1), values.get(2), values.get(3));
        GeneralRepository.getConferenceLinkServiceById(conferenceID).storeOrganizers(organizer.getId());
        GeneralRepository.storeOrganizer(organizer);
    }

}
