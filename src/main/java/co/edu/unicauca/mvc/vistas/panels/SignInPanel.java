package co.edu.unicauca.mvc.vistas.panels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.models.User;
import co.edu.unicauca.mvc.utilities.Elements;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignInPanel extends JPanel
{
    private final StorageService<User> users;

    public SignInPanel(StorageService<User> users) {
        this.users = users;
        createPanel();
    }
    
    private void createPanel() {
        setBackground(new Color(0xD7EAF9));
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 1;
        gbc.weighty = 1; 

        JPanel boxPanel = new JPanel(new GridBagLayout());
        boxPanel.setPreferredSize(Elements.defineSize()); 
        boxPanel.setBackground(new Color(0xD7EAF9)); 

        add(boxPanel, gbc);
    }    
}
