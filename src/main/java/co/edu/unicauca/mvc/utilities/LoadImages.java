package co.edu.unicauca.mvc.utilities;

import javax.swing.ImageIcon;

/**
 *
 * @author LENOVO
 */
public class LoadImages {
    public ImageIcon loadImage(String path) {
        ImageIcon image = new javax.swing.ImageIcon(getClass().getResource(path));  
        return image;
    }
}