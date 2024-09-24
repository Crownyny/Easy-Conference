package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.vistas.panels.MainPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.util.Utilities;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    private final CardPanelManager cardManager;
    
    public MainWindow() {
        super("Easy Conference");
        configWindow();
        
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        MainPanel mainPanel = new MainPanel();
        cardManager.addPanel(mainPanel, "mainPanel");
        getContentPane().add(cardManager.getCardPane());
    }

    public CardPanelManager getCardManager() {
        return cardManager;
    }
    
    private void configWindow()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Utilities.exit(this);    
    }
   
}

    
