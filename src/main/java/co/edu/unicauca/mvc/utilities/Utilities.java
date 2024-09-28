package co.edu.unicauca.mvc.utilities;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author libardo
 */
public class Utilities {

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
            System.out.println("Se elimino");
        }
        for (int i = 0; i < nComponents; i++) {
            String labelText = labelsText.get(i);
            System.out.println(labelText + "-");
            FieldConfig config = fieldConfigs.get(labelText);
            JComponent input =config.getFieldType();
            if (input instanceof CustomTextField) {
                ((CustomTextField) input).resetToInitialState();// Limpia el JTextField
            } else {
                ((CustomDateChooser)input).setInitialState();//Limpia el JDateChooser
            }
        }
    }

}
