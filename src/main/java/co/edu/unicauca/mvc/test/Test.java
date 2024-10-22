package co.edu.unicauca.mvc.test;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.vistas.windows.MainWindow;
import co.edu.unicauca.mvc.vistas.windows.PopUpWindow;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class
 */
public class Test {
    public static void main(String[] args) {
        lookAndField();
        GeneralRepository.initializeManagementService(); // Initialize the general management service
        MainWindow adminWindow = new MainWindow(); // Create the main window and show it
        adminWindow.setVisible(true);       
    }
    
    /**
     * Method to change the appearance of the application
     */
    private static void lookAndField()
    {
        // Set the Nimbus look and feel
        for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
                 } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            }
        }
    }
}
