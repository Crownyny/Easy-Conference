package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.vistas.panels.MainPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    private final CardPanelManager cardManager;
    
    public MainWindow() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        MainPanel mainPanel = new MainPanel();
        cardManager.addPanel(mainPanel, "mainPanel");
        getContentPane().add(cardManager.getCardPane());
        
        // Add window listener for closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public CardPanelManager getCardManager() {
        return cardManager;
    }
   
}

    
