package co.edu.unicauca.mvc.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author libardo
 */
public class GeneralUtilities {

    /**
     * Generates a warning popup
     *
     * @param msg message inside the window
     * @param title title of the window
     */
    public static void warningMessage(String msg, String title) {
        LoadImages loadImages = new LoadImages();
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, loadImages.loadImage("/resources/logo.png"));
    }

    /**
     * Generates an error popup
     *
     * @param msg message inside the window
     * @param title title of the window
     */
    public static void errorMessage(String msg, String title) {
        LoadImages loadImages = new LoadImages();
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, loadImages.loadImage("/resources/warning.png"));
    }

    /**
     * Generates a success popup
     *
     * @param msg message inside the window
     * @param title title of the window
     */
    public static void successMessage(String msg, String title) {
        LoadImages loadImages = new LoadImages();
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, loadImages.loadImage("/resources/success.png"));
    }

    /**
     * Generates a confirmation popup with Yes or No buttons
     *
     * @param msg message inside the window
     * @param title title of the window
     * @return Yes or No
     */
    public static int confirmationMessage(String msg, String title) {
        return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public static void cleanInputs(LinkedHashMap<String, FieldConfig> fieldConfigs) {
        List<String> labelsText = new ArrayList<>(fieldConfigs.keySet());
        int nComponents = fieldConfigs.size();
        if ("".equals(labelsText.get(labelsText.size() - 1))) {
            nComponents--;
            System.out.println("Se eliminó");
        }
        for (int i = 0; i < nComponents; i++) {
            String labelText = labelsText.get(i);
            System.out.println(labelText + "-");
            FieldConfig config = fieldConfigs.get(labelText);
            JComponent input = config.getFieldType();

            switch (input) {
                case CustomTextField customTextField -> customTextField.resetToInitialState(); // Limpia el JTextField
                case CustomDateChooser customDateChooser -> customDateChooser.setInitialState(); // Limpia el JDateChooser
                case JComboBox comboBox -> comboBox.setSelectedIndex(0); // Restablece el JComboBox al primer valor
                default -> {
                }
            }
        }
    }


}
