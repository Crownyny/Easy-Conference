package co.edu.unicauca.mvc.test;

import co.edu.unicauca.mvc.vistas.adminConferencia.MainWindow;
import javax.swing.UIManager;


public class Test {

    
    public static void main(String[] args) {
        seleccionarLookAndField();
        // Add observers to services
        MainWindow adminWindow = new MainWindow();
        adminWindow.setVisible(true);       
    }
    
    
    private static void seleccionarLookAndField()
    {
        for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
                 } catch (Exception ex) {
            }
        }
    }
}
