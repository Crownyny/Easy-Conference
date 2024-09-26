package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Evaluator;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.Utilities;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Default
 */
public class RegisterEvaluatorWindow extends RegisterWindow 
{
    private final int articleID;

    public RegisterEvaluatorWindow( int articleID) {
        super(new JLabel("Registrar Evaluador"), createInputFields());
        this.articleID = articleID;
    }

    
    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre:", new FieldConfig(new JTextField(20)));
        inputFields.put("Apellido:", new FieldConfig(new JTextField(20)));
        inputFields.put("Mail::", new FieldConfig(new JTextField(10)));
        inputFields.put("Afiliation:", new FieldConfig(new JTextField(30)));
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
        ArrayList<String> values = Elements.extractTextFields(fieldConfigs);
        
        try{
            Evaluator evaluator = new Evaluator(values.get(0),values.get(1), values.get(2), values.get(3));
            GeneralRepository.getArticleLinkServiceById(articleID).storeEvaluators(evaluator.getId());
            GeneralRepository.storeEvaluator(evaluator);
              
        } catch (NumberFormatException ex) {
            Utilities.warningMessage("El id debe ser numérico", "Formato de costo inválido");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
