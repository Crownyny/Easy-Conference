package co.edu.unicauca.mvc.utilities;

import javax.swing.JOptionPane;

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

}

