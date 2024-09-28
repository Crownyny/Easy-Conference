package co.edu.unicauca.mvc.vistas.genericPanels;

import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.Utilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class RegisterPanel extends JPanel{
    protected JLabel titleLabel;
    protected LinkedHashMap<String, FieldConfig> fieldConfigs;

    public RegisterPanel(JLabel titleLabel, LinkedHashMap<String, FieldConfig> fieldConfigs) {
        this.titleLabel = titleLabel;
        this.fieldConfigs = fieldConfigs;
        showGui();
    }
    
    
    private void showGui() {
        setLayout(new BorderLayout());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        List<String> labelsText = new ArrayList<>(fieldConfigs.keySet());
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int panelHeight = (int) (screenHeight * 0.55);
        int panelWidth = (int) (screenWidth * 0.35);

        int nComponents = fieldConfigs.size();
        if ("".equals(labelsText.get(labelsText.size() - 1))) {
            nComponents--;
        }

        int componentHeight = (int) (panelHeight * .15);

        int panelNorthHeight = (int) (panelHeight * .15);
        int panelCenterHeight = (int) (componentHeight * (nComponents + 1));

        JPanel panelNorth = new JPanel(new GridBagLayout());
        panelNorth.setPreferredSize(new Dimension(panelWidth, panelNorthHeight));

        int titleFontSize = Math.min(panelWidth, panelHeight) / 20;
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, titleFontSize));
        panelNorth.add(titleLabel);
        panelNorth.setBackground(new Color(0x3c647c));

        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBackground(new Color(0xD7EAF9));
        panelCenter.setPreferredSize(new Dimension(panelWidth, panelCenterHeight));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;  // Mantiene el tamaño preferido

        double inputWidthPercentage = 0.70;
        int inputWidth = (int) (panelWidth * inputWidthPercentage);  // Calcula el ancho del input

        for (int i = 0; i < nComponents; i++) {
            FieldConfig config = fieldConfigs.get(labelsText.get(i));

            // Recupera el JComponent (puede ser JTextField u otro)
            JComponent field = config.getFieldType();

            // Ajusta el tamaño preferido del input
            field.setPreferredSize(new Dimension(inputWidth, field.getPreferredSize().height));

            // Configuración de GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.0;  // No se expande horizontalmente
            panelCenter.add(field, gbc);  // Añade el JComponent al panel
        }

        JPanel panelButton = new JPanel();
        panelButton.setBackground(new Color(0xD7EAF9));
        panelButton.setPreferredSize(new Dimension(panelWidth, componentHeight));

        int buttonFontSize = Math.min(panelWidth, panelHeight) / 30;

        try {
            if (fieldConfigs.size() != nComponents) {
                // Add the "Assign Author" button
                FieldConfig config = fieldConfigs.get("");
                JComponent field = config.getFieldType();
                JButton assignButton = Elements.addButton((JButton) field, buttonFontSize);

                assignButton.addActionListener(e -> extraButtonAction());
                panelButton.add(assignButton);
            }

            // Load and scale the icon for the "Register" button
            BufferedImage iconRegister = ImageIO.read(getClass().getResource("/resources/save.png"));
            Image iconScaled = iconRegister.getScaledInstance(buttonFontSize, buttonFontSize, Image.SCALE_SMOOTH);

            // Create the "Register" button
            JButton registerButton = Elements.addButton(new JButton(" Registrar"), buttonFontSize);
            registerButton.setIcon(new ImageIcon(iconScaled));

            registerButton.addActionListener(e -> registerAction());
            panelButton.add(registerButton);

        } catch (IOException e) {}

        add(panelCenter, BorderLayout.CENTER);
        add(panelNorth, BorderLayout.NORTH);
        add(panelButton, BorderLayout.SOUTH);
    }

    protected void cleanInputs() {
        Utilities.cleanInputs(fieldConfigs);
    }

    protected abstract void registerAction();

    protected void extraButtonAction() {
    }
;


}
