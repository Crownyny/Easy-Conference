package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.controllers.UserManagementService;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import co.edu.unicauca.mvc.models.User;
import co.edu.unicauca.mvc.vistas.panels.LogInPanel;
import co.edu.unicauca.mvc.vistas.panels.MainPanel;
import co.edu.unicauca.mvc.vistas.panels.SignInPanel;
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
        linkPanels();
        getContentPane().add(cardManager.getCardPane());
    }
    
    private void linkPanels()
    {
        MemoryArrayListRepository<UserManagementService> userRepository = new MemoryArrayListRepository<>();
        StorageService<UserManagementService> userService = new StorageService<>(userRepository);
        userService.store(new UserManagementService(
                new User("Julian","Meneses","something@gmail.com","123"),
        new MemoryArrayListRepository<>(),
        new MemoryArrayListRepository<>()));
        
        LogInPanel logInPanel = new LogInPanel(this, userService);
        SignInPanel signInPanel = new SignInPanel(this,userService);
        MainPanel mainPanel = new MainPanel();

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

    
