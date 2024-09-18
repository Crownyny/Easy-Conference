
package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Conference;
import co.edu.unicauca.mvc.models.Organizer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author Default
 */
public class MainAdminWindow extends javax.swing.JFrame {

    private final Map<Class<? extends JInternalFrame>, JInternalFrame> internalFrames = new HashMap<>();
    private final Map<Class<?>, StorageService<?>> services = new HashMap<>();
    private JDesktopPane mainDesktopPane;
    private final JPanel cardPane;

    public void associateService(Class<?> serviceClass, StorageService<?> serviceObject) {
        services.put(serviceClass, serviceObject);
        removeInternalFrameForService(serviceClass);
        relateInternalFramesToDesktopPane();
    }

    private void removeInternalFrameForService(Class<?> serviceClass) {
        if (serviceClass.equals(Conference.class) && internalFrames.containsKey(ListConferencesWindow.class)) {
            mainDesktopPane.remove(internalFrames.get(ListConferencesWindow.class));
            internalFrames.remove(ListConferencesWindow.class);
        } else if (serviceClass.equals(Organizer.class) && internalFrames.containsKey(ListOrganizersWindow.class)) {
            mainDesktopPane.remove(internalFrames.get(ListOrganizersWindow.class));
            internalFrames.remove(ListOrganizersWindow.class);
        } else if (serviceClass.equals(Article.class) && internalFrames.containsKey(ListArticlesWindow.class)) {
            mainDesktopPane.remove(internalFrames.get(ListArticlesWindow.class));
            internalFrames.remove(ListArticlesWindow.class);
        }
    }

    private void relateInternalFramesToDesktopPane() {
        // Clear the JDesktopPane
        mainDesktopPane.removeAll();

        // Add frames based on the associated service
        if (!internalFrames.containsKey(ViewStatisticsWindow.class)) {
            internalFrames.put(ViewStatisticsWindow.class, new ViewStatisticsWindow());
        }
        mainDesktopPane.add(internalFrames.get(ViewStatisticsWindow.class));

        if (services.containsKey(Conference.class)) {
            if (!internalFrames.containsKey(ListConferencesWindow.class)) {
                internalFrames.put(ListConferencesWindow.class, 
                    new ListConferencesWindow(this, (StorageService<Conference>) services.get(Conference.class)));
            }
            mainDesktopPane.add(internalFrames.get(ListConferencesWindow.class));
        }

        if (services.containsKey(Organizer.class)) {
            if (!internalFrames.containsKey(ListOrganizersWindow.class)) {
                internalFrames.put(ListOrganizersWindow.class, 
                    new ListOrganizersWindow((StorageService<Organizer>) services.get(Organizer.class)));
            }
            mainDesktopPane.add(internalFrames.get(ListOrganizersWindow.class));
        }

        if (services.containsKey(Article.class)) {
            if (!internalFrames.containsKey(ListArticlesWindow.class)) {
                internalFrames.put(ListArticlesWindow.class, 
                    new ListArticlesWindow((StorageService<Article>) services.get(Article.class)));
            }
            mainDesktopPane.add(internalFrames.get(ListArticlesWindow.class));
        }
    }

    public MainAdminWindow() {
        cardPane = new JPanel(new CardLayout());
        showGui();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void showGui() {
        // Maximize the window and remove decorations
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;

        // Create and configure North panel
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(new Color(0xD7EAF9));
        panelNorth.setLayout(new GridBagLayout());
        int panelNorthHeight = (int) (screenHeight * 0.2);
        panelNorth.setPreferredSize(new Dimension(screenSize.width, panelNorthHeight));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.insets = new Insets((int) (panelNorthHeight * 0.01), 0, (int) (panelNorthHeight * 0.1), 0); // Padding

        // Add title label to the North panel
        JLabel titleLabel = new JLabel("");
        setLabel(titleLabel, "EASY CONFERENCE", (int) (panelNorthHeight * 0.18), new Color(0x2c4464));
        panelNorth.add(titleLabel, gbc);

        // Configure GridBagConstraints for buttons
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        
        // Define buttons and actions
        String[] mainPanelLabels = {"Gestionar conferencias"};
        ActionListener[] mainPanelActions = {e -> setVisibility(VisibilityState.LIST_CONFERENCES)};

        String[] conferencePanelLabels = {"Gestionar organizadores", "Gestionar artículos", "Ver estadísticas"};
        ActionListener[] conferencePanelActions = {
            e -> setVisibility(VisibilityState.LIST_ORGANIZERS),
            e -> setVisibility(VisibilityState.LIST_ARTICLES),
            e -> setVisibility(VisibilityState.VIEW_STATISTICS)
        };

        // Create and add panels to CardLayout
        JPanel buttonsMainPanel = createButtonPanel(mainPanelLabels, mainPanelActions);
        JPanel buttonsConferencePanel = createButtonPanel(conferencePanelLabels, conferencePanelActions);

        cardPane.add(buttonsMainPanel, "mainPanel");
        cardPane.add(buttonsConferencePanel, "conferencePanel");

        panelNorth.add(cardPane, gbc);

        // Create and configure Center panel
        mainDesktopPane = new JDesktopPane(); // Create the JDesktopPane
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(Color.GREEN);
        panelCenter.add(mainDesktopPane, BorderLayout.CENTER); // Add JDesktopPane to Center panel

        // Create and configure South panel
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(new Color(0x696A78));
        int panelSouthHeight = (int) (screenHeight * 0.1);
        panelSouth.setPreferredSize(new Dimension(screenSize.width, panelSouthHeight));
        JLabel logoLabel = new JLabel("");
        setOrgIcon(logoLabel, "/resources/logo.png", "", (int) (panelNorthHeight * 0.18), new Color(0x2c4464));
        panelSouth.add(logoLabel, gbc);

        // Add panels to the content pane
        this.getContentPane().add(panelNorth, BorderLayout.NORTH);
        this.getContentPane().add(panelCenter, BorderLayout.CENTER);
        this.getContentPane().add(panelSouth, BorderLayout.SOUTH);

        // Add ComponentListener to adjust font size dynamically
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustFontSize(titleLabel, buttonsMainPanel);
            }
        });
    }

    private void adjustFontSize(JLabel label, JPanel buttonsMainPanel) {
        // Get current window size
        int width = this.getWidth();
        int height = this.getHeight();

        // Adjust title label font size
        int titleFontSize = Math.min(width, height) / 30; 
        label.setFont(new Font("Leelawadee UI", Font.BOLD, titleFontSize));

        // Adjust button font size
        Component[] components = buttonsMainPanel.getComponents();
        int buttonFontSize = Math.min(width, height) / 48; 
        for (Component component : components) {
            if (component instanceof JButton button) {
                button.setFont(new Font("Cascadia Code", Font.PLAIN, buttonFontSize));
            }
        }

        // Revalidate and repaint to update layout and centering
        buttonsMainPanel.revalidate();
        buttonsMainPanel.repaint();
    }

    private void setOrgIcon(JLabel label, String source, String text, int fontsize, Color textColor) {
        Image img1 = new ImageIcon(getClass().getResource(source)).getImage();
        ImageIcon img2 = new ImageIcon(img1.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        label.setIcon(img2);
        label.setText("<html><center>" + text + "</center></html>");
        label.setFont(new Font("Lucida Console", Font.BOLD, fontsize));
        label.setForeground(textColor);
    }

    private void setLabel(JLabel label, String text, int fontsize, Color textColor) {
        label.setText("<html><center>" + text + "</center></html>");
        label.setFont(new Font("Lucida Console", Font.BOLD, fontsize));
        label.setForeground(textColor);
    }

    private JButton addButton(String buttonText, JPanel container) {
        JButton myButton = new JButton(buttonText);
        container.add(myButton);
        container.add(Box.createHorizontalStrut(20)); 
        myButton.setBorderPainted(false);
        myButton.setBackground(new Color(0x2c4464)); // Return to transparent background
        myButton.setForeground(Color.WHITE);
        myButton.setFont(new Font("Lucida Console", Font.BOLD, 1)); // Fontsize doesn't matter
        myButton.setFocusPainted(false);
        myButton.setContentAreaFilled(false);
        myButton.setOpaque(true); // Make the button opaque from the start

        myButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                myButton.setBackground(new Color(52, 112, 224)); // Hover color with transparency
                myButton.repaint(); // Repaint the button
                myButton.getParent().repaint(); // Ensure the parent container is also repainted
                myButton.getParent().revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                myButton.setBackground(new Color(0x2c4464)); // Return to transparent background
                myButton.repaint(); // Repaint the button and the container
                myButton.getParent().repaint(); // Ensure the parent container is also repainted
                myButton.getParent().revalidate();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                myButton.setBackground(new Color(52, 112, 224)); // Adjust background if necessary
                myButton.repaint();
                myButton.getParent().repaint(); // Repaint the button's container
                myButton.getParent().revalidate(); // Revalidate the layout of the container
            }
        });

        return myButton;
    }

    private enum VisibilityState {
        VIEW_STATISTICS,
        LIST_CONFERENCES,
        LIST_ORGANIZERS,
        LIST_ARTICLES,
        NONE
    }

    private void setVisibility(VisibilityState state) {
        for (JInternalFrame frame : internalFrames.values()) {
            frame.setVisible(false);
        }

        switch (state) {
            case VIEW_STATISTICS -> setFrameVisible(ViewStatisticsWindow.class);
            case LIST_CONFERENCES -> setFrameVisible(ListConferencesWindow.class);
            case LIST_ORGANIZERS -> setFrameVisible(ListOrganizersWindow.class);
            case LIST_ARTICLES -> setFrameVisible(ListArticlesWindow.class);
            case NONE -> {
            }
        }
    }

    private void setFrameVisible(Class<? extends JInternalFrame> frameClass) {
        JInternalFrame frame = internalFrames.get(frameClass);
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    // Method to configure panel with common settings
    private JPanel createButtonPanel(String[] buttonLabels, ActionListener[] actions) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xD7EAF9));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = addButton(buttonLabels[i], panel);
            button.addActionListener(actions[i]);
        }

        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    public void showPanel(String paneName) {
        CardLayout cardLayout = (CardLayout) cardPane.getLayout();

        cardLayout.show(cardPane, paneName);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
