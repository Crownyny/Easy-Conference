package co.edu.unicauca.mvc.vistas.conferencePanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Conference;
import co.edu.unicauca.mvc.utilities.CustomDateChooser;
import co.edu.unicauca.mvc.utilities.CustomTextField;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.GeneralUtilities;
import co.edu.unicauca.mvc.vistas.genericPanels.RegisterPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.windows.AssignTopicWindow;
import com.toedter.calendar.JDateChooser;
import java.awt.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RegisterConferencePanel extends RegisterPanel{
    private final CardPanelManager cardManager;
    private final int userID;
    private final ArrayList<String> selectedTopics; 

    public RegisterConferencePanel (CardPanelManager cardManager,int idUser) {
        super(new JLabel("Registrar Conferencia"), createInputFields());
        selectedTopics = new ArrayList<>();
        this.userID = idUser;
        this.cardManager = cardManager;
    }
    
    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        JDateChooser startDate = new CustomDateChooser("Fecha de inicio: ");
        startDate.setDateFormatString("dd/MM/yyyy");

        JDateChooser endDate = new CustomDateChooser("Fecha de fin: ");
        endDate.setDateFormatString("dd/MM/yyyy");

        int maxLength = 9; 

        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre:", new FieldConfig(new CustomTextField("Nombre: ")));
        inputFields.put("Fecha de inicio:", new FieldConfig(startDate));
        inputFields.put("Fecha de fin:", new FieldConfig(endDate));
        inputFields.put("Costo de inscripcion:", new FieldConfig(Elements.createNumberField(maxLength,"Costo de incripción: ")));
        inputFields.put("Ubicacion:", new FieldConfig(new CustomTextField("Ubicacion: ")));
        inputFields.put("", new FieldConfig(new JButton("Agregar tema")));
        
        return inputFields;
    }
    
 
    @Override
    protected void registerAction() {
        ArrayList<String> values = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fieldConfigs.values().stream()
            .map(FieldConfig::getFieldType)
            .forEach(field -> {
            switch (field) {
                case JTextField jTextField -> 
                    values.add(jTextField.getText());
                case JDateChooser jDateChooser -> 
                    values.add(jDateChooser.getDate() != null ? dateFormat.format(jDateChooser.getDate()) : "");
                default -> {
                }
            }
        });

        for(String value: values){
            System.out.println(value);
        }
        

        try {
            Date startDate = dateFormat.parse(values.get(1));
            Date endDate = dateFormat.parse(values.get(2));

            if (endDate.before(startDate)) {
                GeneralUtilities.warningMessage("La fecha de fin debe ser posterior a la fecha de inicio", "Fecha no válida");
                return;
            }
            
            if(selectedTopics.isEmpty())
            {
                GeneralUtilities.warningMessage("Debe seleccionar al menos un tema", "Falta seleccionar temas");
                return;
            }

            float cost = Float.parseFloat(values.get(3));
            Conference conference = new Conference(values.get(0), startDate, endDate, cost, values.get(4),selectedTopics);
            GeneralRepository.getUserLinkServiceById(userID).storeConferences(conference.getId());
            GeneralRepository.storeConference(conference);
            GeneralUtilities.successMessage("Conferencia creada correctamente", "Creación de conferencia");
            cleanInputs();
            
            cardManager.showPanel("listPanel");

        } catch (NumberFormatException ex) {
            GeneralUtilities.warningMessage("El costo debe ser numérico", "Formato de costo inválido");
        } catch (ParseException ex) {
            GeneralUtilities.warningMessage("La fecha debe seguir el formato dd/MM/yyyy", "Formato de fecha inválido");
        }
    }
    
    @Override
    protected void extraButtonAction() {
        String[] conferenceTopics = {
            "Inteligencia Artificial", "Ciencia de Datos", "Ciberseguridad", "Internet de las Cosas",
            "Blockchain", "Desarrollo Web", "Computación en la Nube", "Realidad Virtual", 
            "Desarrollo de Software", "Ingeniería de Sistemas", "Automatización de Pruebas",
            "Machine Learning", "Big Data", "Robótica", "Computación Cuántica", 
            "Sistemas Embebidos", "Algoritmos y Complejidad", "Redes de Computadoras", 
            "Bases de Datos", "Tecnologías Emergentes","Otro"
        };
        
       Window window = SwingUtilities.getWindowAncestor(this);
        
        if (window instanceof JFrame parentFrame) {
            AssignTopicWindow.openAssignTopicWindow(parentFrame, selectedTopics, conferenceTopics);
        }     
        
        
    }   
}
