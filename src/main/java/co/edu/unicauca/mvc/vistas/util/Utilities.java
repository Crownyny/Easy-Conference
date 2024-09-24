package co.edu.unicauca.mvc.vistas.util;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Utilities {
    
    // Static method to add a window listener that closes the application
    public static void exit(Window window) {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });
    }
    
    
}
