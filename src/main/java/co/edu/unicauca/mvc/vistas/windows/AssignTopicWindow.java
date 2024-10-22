package co.edu.unicauca.mvc.vistas.windows;

import co.edu.unicauca.mvc.utilities.CustomScrollBarUI;
import co.edu.unicauca.mvc.utilities.Components;

import static javax.swing.SwingUtilities.getWindowAncestor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Default
 */
public class AssignTopicWindow extends JDialog  {

    private final List<String> myTopics;
    private final String[] conferenceTopics;

    /**
     * Creates new form AssignWindow
     *
     * @param parent Parent frame
     * @param myTopics Selected Topics
     * @param conferenceTopics Possibles topics of the conference
     */
    public AssignTopicWindow(JFrame parent, ArrayList<String> myTopics, String[] conferenceTopics) {
        super(parent, true);
        this.myTopics = myTopics;
        this.conferenceTopics = conferenceTopics;
        showGui();
    }

    private void showGui() {
        setLayout(new BorderLayout());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int panelHeight = (int) (screenHeight * 0.55);
        int panelWidth = (int) (screenWidth * 0.35);
        int componentHeight = (int) (panelHeight * .15);

        int panelNorthHeight = (int) (panelHeight * 0.1);
        int panelCenterHeight = (int) (panelHeight * 0.85);
        int panelSouthHeight = (int) (panelHeight * 0.15);

        int totalHeight = panelNorthHeight + panelCenterHeight + panelSouthHeight;
        setSize(panelWidth, totalHeight);

        JPanel panelNorth = new JPanel(new GridBagLayout());
        panelNorth.setPreferredSize(new Dimension(panelWidth, panelNorthHeight));

        int titleFontSize = Math.min(panelWidth, panelHeight) / 20;
        JLabel titleLabel = new JLabel("Seleccionar temas");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, titleFontSize));
        panelNorth.add(titleLabel);
        panelNorth.setBackground(new Color(0x3c647c));

        JPanel panelCenter = new JPanel(new GridLayout(0, 2));
        panelCenter.setBackground(new Color(0xD7EAF9));
        List<JCheckBox> checkboxes = new ArrayList<>();
        for (String topic : conferenceTopics) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setText(topic);
            checkboxes.add(checkBox);
            panelCenter.add(checkBox);
        }

        // Añadir scroll al panel central
        JScrollPane scrollPane = new JScrollPane(panelCenter);
        scrollPane.setPreferredSize(new Dimension(panelWidth, panelCenterHeight));
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        JPanel panelButton = new JPanel(new GridBagLayout());
        //Condiciones para centrar el boton en el panel
        GridBagConstraints gbcButton = new GridBagConstraints();

        // Configuración para centrar el botón verticalmente
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.weighty = 2.0;  // Hace que el componente se centre verticalmente
        gbcButton.anchor = GridBagConstraints.CENTER;  // Centrarlo horizontal y verticalmente

        panelButton.setBackground(new Color(0xD7EAF9));
        panelButton.setPreferredSize(new Dimension(panelWidth, componentHeight));

        int buttonFontSize = Math.min(panelWidth, panelHeight) / 30;

        try {
            BufferedImage iconRegister = ImageIO.read(getClass().getResource("/resources/save.png"));
            Image iconScaled = iconRegister.getScaledInstance(buttonFontSize, buttonFontSize, Image.SCALE_SMOOTH);

            JButton registerButton = Components.addButton(new JButton(" Registrar"), buttonFontSize);
            registerButton.setIcon(new ImageIcon(iconScaled));

            registerButton.addActionListener(e -> {
                myTopics.clear();
                for (int i = 0; i < conferenceTopics.length; i++) {
                    if (checkboxes.get(i).isSelected()) {
                        myTopics.add(conferenceTopics[i]);
                    }
                }
                if (myTopics.isEmpty()) {
                    new PopUpWindow((JFrame) getWindowAncestor(this), 
                        PopUpWindow.PopUpType.ERROR, 
                        "Debe seleccionar al menos un tema");
                } else {
                    new PopUpWindow((JFrame) getWindowAncestor(this), 
                        PopUpWindow.PopUpType.SUCCESS, 
                        "Temas asignados con éxito");
                    this.dispose();
                }
            });
            panelButton.add(registerButton, gbcButton);

        } catch (IOException e) {
        }

        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(panelNorth, BorderLayout.NORTH);
        this.getContentPane().add(panelButton, BorderLayout.SOUTH);
    }

    public static void openAssignTopicWindow(JFrame parent, ArrayList<String> myTopics, String[] conferenceTopics) {
        AssignTopicWindow dialog = new AssignTopicWindow(parent, myTopics, conferenceTopics);
        dialog.setTitle("Asignar Temas");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(parent); 
        dialog.setVisible(true);  
    }
}
