package co.edu.unicauca.mvc.vistas.panels;

import co.edu.unicauca.mvc.controllers.ArticleManagementService;
import co.edu.unicauca.mvc.controllers.ConferenceManagementService;
import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.models.Organizer;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.vistas.adminConferencia.ListArticlesWindow;
import co.edu.unicauca.mvc.vistas.adminConferencia.ListConferencesWindow;
import co.edu.unicauca.mvc.vistas.adminConferencia.ListOrganizersWindow;
import co.edu.unicauca.mvc.vistas.adminConferencia.ViewStatisticsWindow;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
    private final Map<Class<? extends JInternalFrame>, JInternalFrame> internalFrames = new HashMap<>();
    private final Map<Class<?>, StorageService<?>> services = new HashMap<>();
    private JDesktopPane mainDesktopPane;
    private final CardPanelManager cardManager;

    public void associateService(Class<?> serviceClass, StorageService<?> serviceObject) {
        services.put(serviceClass, serviceObject);
        removeInternalFrameForService(serviceClass);
        relateInternalFramesToDesktopPane();
    }
    
    private void removeInternalFrameForService(Class<?> serviceClass) {
        if (serviceClass.equals(ConferenceManagementService.class) && internalFrames.containsKey(ListConferencesWindow.class)) {
            mainDesktopPane.remove(internalFrames.get(ListConferencesWindow.class));
            internalFrames.remove(ListConferencesWindow.class);
        
        } else if (serviceClass.equals(Organizer.class) && internalFrames.containsKey(ListOrganizersWindow.class)) {
            mainDesktopPane.remove(internalFrames.get(ListOrganizersWindow.class));
            internalFrames.remove(ListOrganizersWindow.class);
        
        } else if (serviceClass.equals(ArticleManagementService.class) && internalFrames.containsKey(ListArticlesWindow.class)) {
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

        if (services.containsKey(ConferenceManagementService.class)) {
            if (!internalFrames.containsKey(ListConferencesWindow.class)) {
                internalFrames.put(ListConferencesWindow.class, 
                    new ListConferencesWindow(this, (StorageService<ConferenceManagementService>) services.get(ConferenceManagementService.class)));
            }
            mainDesktopPane.add(internalFrames.get(ListConferencesWindow.class));
            services.get(ConferenceManagementService.class).addObserver((Observer) internalFrames.get(ListConferencesWindow.class));
        }

        if (services.containsKey(Organizer.class)) {
            if (!internalFrames.containsKey(ListOrganizersWindow.class)) {
                internalFrames.put(ListOrganizersWindow.class, 
                    new ListOrganizersWindow((StorageService<Organizer>) services.get(Organizer.class)));
            }
            mainDesktopPane.add(internalFrames.get(ListOrganizersWindow.class));
            services.get(Organizer.class).addObserver((Observer) internalFrames.get(ListOrganizersWindow.class));
        }

        if (services.containsKey(ArticleManagementService.class)) {
            if (!internalFrames.containsKey(ListArticlesWindow.class)) {
                internalFrames.put(ListArticlesWindow.class, 
                    new ListArticlesWindow( (StorageService<ArticleManagementService>) services.get(ArticleManagementService.class)));

            }
            mainDesktopPane.add(internalFrames.get(ListArticlesWindow.class));
            services.get(ArticleManagementService.class).addObserver((Observer) internalFrames.get(ListArticlesWindow.class));
        }
        
    }

    public MainPanel() {
        super(new BorderLayout()); // Main panel replacing the JFrame's content
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        createMainPanel(); 
    }
    
    
    private void createMainPanel() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;

        // Create and configure the north panel
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

        // Add title label to the north panel
        JLabel titleLabel = new JLabel("");
        setLabel(titleLabel, "EASY CONFERENCE", (int) (panelNorthHeight * 0.18), new Color(0x2c4464));
        panelNorth.add(titleLabel, gbc);

        // Set GridBagConstraints for buttons
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Define buttons and their actions
        String[] mainPanelLabels = {"Gestionar conferencias"};
        ActionListener[] mainPanelActions = {e -> setVisibility(MainPanel.VisibilityState.LIST_CONFERENCES)};

        String[] conferencePanelLabels = {"Gestionar organizadores", "Gestionar artículos", "Ver estadísticas", "Regresar"};
        ActionListener[] conferencePanelActions = {
            e -> setVisibility(MainPanel.VisibilityState.LIST_ORGANIZERS),
            e -> setVisibility(MainPanel.VisibilityState.LIST_ARTICLES),
            e -> setVisibility(MainPanel.VisibilityState.VIEW_STATISTICS),
            e -> {
                setVisibility(MainPanel.VisibilityState.NONE);
                cardManager.showPanel("mainPanel");
            }
        };

        // Create and add button panels to CardLayout
        JPanel buttonsMainPanel = createButtonPanel(mainPanelLabels, mainPanelActions);
        JPanel buttonsConferencePanel = createButtonPanel(conferencePanelLabels, conferencePanelActions);

        cardManager.addPanel(buttonsMainPanel, "mainPanel");
        cardManager.addPanel(buttonsConferencePanel, "conferencePanel");

        panelNorth.add(cardManager.getCardPane(), gbc);

        // Create and configure the center panel with JDesktopPane
        mainDesktopPane = new JDesktopPane();
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(Color.GREEN);
        panelCenter.add(mainDesktopPane, BorderLayout.CENTER); // Add JDesktopPane to the center panel

        // Create and configure the south panel
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(new Color(0x696A78));
        int panelSouthHeight = (int) (screenHeight * 0.1);
        panelSouth.setPreferredSize(new Dimension(screenSize.width, panelSouthHeight));
        JLabel logoLabel = new JLabel("");
        setOrgIcon(logoLabel, "/resources/logo.png", "", (int) (panelNorthHeight * 0.18), new Color(0x2c4464));
        panelSouth.add(logoLabel, gbc);

        // Add panels to the main panel
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        // Adjust font size dynamically based on window size
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustFontSize(titleLabel, buttonsMainPanel, buttonsConferencePanel);
            }
        });
    }
    
    private void adjustFontSize(JLabel label, JPanel... panels) {
        // Get current window size
        int width = this.getWidth();
        int height = this.getHeight();

        // Adjust title label font size
        int titleFontSize = Math.min(width, height) / 30; 
        label.setFont(new Font("Leelawadee UI", Font.BOLD, titleFontSize));

        // Adjust button font size for each panel
        int buttonFontSize = Math.min(width, height) / 48; 
        for (JPanel panel : panels) {
            Component[] components = panel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton button) {
                    button.setFont(new Font("Cascadia Code", Font.PLAIN, buttonFontSize));
                }
            }

            // Revalidate and repaint to update layout and centering for each panel
            panel.revalidate();
            panel.repaint();
        }
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
            JButton button = Elements.addButton(buttonLabels[i], panel);
            button.addActionListener(actions[i]);
        }

        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    public CardPanelManager getCardManager() {
        return cardManager;
    }
}
