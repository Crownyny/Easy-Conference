 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import co.edu.unicauca.mvc.models.Conference;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.Utilities;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
/**
 *
 * @author Default
 */
public class RegisterConferenceWindow extends RegisterWindow {
    
    private final int userID;
    private final ArrayList<String> selectedTopics; 
    /**
     * Creates new form VtnListarArticulos
     * @param idUser
     */
    public RegisterConferenceWindow (int idUser) {
        super(new JLabel("Registrar Conferencia"), createInputFields());
        selectedTopics = new ArrayList<>();
        this.userID = idUser;
    }
    
    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        JDateChooser startDate = new JDateChooser();
        startDate.setDateFormatString("dd/MM/yyyy");

        JDateChooser endDate = new JDateChooser();
        endDate.setDateFormatString("dd/MM/yyyy");

        int maxLength = 9; 

        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre:", new FieldConfig(new JTextField(20)));
        inputFields.put("Fecha de inicio:", new FieldConfig(startDate));
        inputFields.put("Fecha de fin:", new FieldConfig(endDate));
        inputFields.put("Costo de inscripcion:", new FieldConfig(Elements.createNumberField(maxLength)));
        inputFields.put("Ubicacion:", new FieldConfig(new JTextField(20)));
        inputFields.put("", new FieldConfig(new JButton("Agregar tema")));
        
        return inputFields;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                Utilities.warningMessage("La fecha de fin debe ser posterior a la fecha de inicio", "Fecha no válida");
                return;
            }
            
            if(selectedTopics.isEmpty())
            {
                Utilities.warningMessage("Debe seleccionar al menos un tema", "Falta seleccionar temas");
                return;
            }

            float cost = Float.parseFloat(values.get(3));
            Conference conference = new Conference(values.get(0), startDate, endDate, cost, values.get(4),selectedTopics);
            GeneralRepository.getUserLinkServiceById(userID).storeConferences(conference.getId());
            GeneralRepository.storeConference(conference);

        } catch (NumberFormatException ex) {
            Utilities.warningMessage("El costo debe ser numérico", "Formato de costo inválido");
        } catch (ParseException ex) {
            Utilities.warningMessage("La fecha debe seguir el formato dd/MM/yyyy", "Formato de fecha inválido");
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
        AssignTopicWindow objTopicWindow =
            new AssignTopicWindow(this.selectedTopics,conferenceTopics);
        objTopicWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        objTopicWindow.setVisible(true);       
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
