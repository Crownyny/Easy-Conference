package co.edu.unicauca.mvc.vistas.panels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.models.User;
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
        boxPanel.setPreferredSize(defineSize()); 
        boxPanel.setBackground(new Color(0xD7EAF9)); 
        //addRowsToBoxPanel(boxPanel);

        add(boxPanel, gbc);
    }    
    
    /*
    private void addRowsToBoxPanel(JPanel boxPanel) 
    {
        List<JTextField> inputs = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // First row with the centered icon
        gbc.gridy = 0;
        gbc.weighty = 0.4;
        int logoFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 5;

        JPanel row1 = createRowPanel(new Color(0xD7EAF9));
        row1.setLayout(new GridBagLayout());

        try {
            BufferedImage iconRegister = ImageIO.read(getClass().getResource("/resources/login_icon.png"));
            Image iconScaled = iconRegister.getScaledInstance(logoFontSize, logoFontSize, Image.SCALE_SMOOTH);

            // Apply a color scale filter to darken the icon
            BufferedImage bufferedIcon = new BufferedImage(iconScaled.getWidth(null), iconScaled.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedIcon.createGraphics();
            g2d.drawImage(iconScaled, 0, 0, null);
            g2d.dispose();

            float[] scales = { 0.5f, 0.5f, 0.7f, 1.0f };
            RescaleOp op = new RescaleOp(scales, new float[4], null);
            bufferedIcon = op.filter(bufferedIcon, null);

            JLabel iconLabel = new JLabel(new ImageIcon(bufferedIcon));

            GridBagConstraints iconConstraints = new GridBagConstraints();
            iconConstraints.gridx = 0;
            iconConstraints.gridy = 0;
            iconConstraints.anchor = GridBagConstraints.CENTER;
            row1.add(iconLabel, iconConstraints);
        } catch (IOException e) {}

        boxPanel.add(row1, gbc);

        // Rows with text fields
        gbc.weighty = 0.45;
        String[] texts = { "  Email", "  Contraseña" };

        for (int i = 1; i <= 2; i++) {
            gbc.gridy = i;
            JPanel row = createRowPanel(new Color(0xD7EAF9));

            JTextField inputField = createInputField(texts[i - 1]);
            inputField.setPreferredSize(new Dimension(1, (int) (boxPanel.getPreferredSize().height * 0.1)));

            row.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;

            inputs.add(inputField);
            row.add(inputField, constraints);
            boxPanel.add(row, gbc);
        }

        // "Ingresar" button
        gbc.gridy = 3;
        gbc.weighty = 0.2;
        int buttonFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 21;
        JButton mainButton = addButton(new JButton("Ingresar"), buttonFontSize);
        mainButton.addActionListener(e -> loginAction(inputs));
        boxPanel.add(mainButton, gbc);

        // Final label
        gbc.weighty = 0.3;
        gbc.gridy = 4;
        int labelFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 27;
        boxPanel.add(createLabel("No tienes una cuenta? Registrate!", labelFontSize), gbc);
    }*/

    
    public Dimension defineSize() 
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * .25);
        int height = (int) (screenSize.height * .4);
        return new Dimension(width, height);
    }    
}
