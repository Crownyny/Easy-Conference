package co.edu.unicauca.mvc.vistas.windows;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.*;
import co.edu.unicauca.mvc.vistas.mainWindowPanels.LogInPanel;
import co.edu.unicauca.mvc.vistas.mainWindowPanels.MainPanel;
import co.edu.unicauca.mvc.vistas.mainWindowPanels.SignInPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.util.Utilities;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    private final CardPanelManager cardManager;
    
    public MainWindow() {
        super("Easy Conference");
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        configWindow();       
        linkPanels();
        getContentPane().add(cardManager.getCardPane());
    }
    
    private void linkPanels()
    {

        GeneralRepository.storeUser(
                new User("a@a.com","123","Julian","Meneses"));
        
        LogInPanel logInPanel = new LogInPanel(cardManager);
        SignInPanel signInPanel = new SignInPanel(cardManager);
        MainPanel mainPanel = new MainPanel(cardManager);

        cardManager.addPanel(logInPanel, "logInPanel");
        cardManager.addPanel(signInPanel, "signInPanel");
        cardManager.addPanel(mainPanel, "mainPanel");
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

    
