package co.edu.unicauca.mvc.vistas.windows;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.utilities.Components;
import co.edu.unicauca.mvc.vistas.articlePanels.ListArticlesPanel;
import co.edu.unicauca.mvc.vistas.articlePanels.RegisterArticlePanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ArticleWindow extends JInternalFrame {

    private final CardPanelManager cardManager;
    private final int conferenceID;

    public ArticleWindow (int conferenceID) {

        this.conferenceID =conferenceID;
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        linkPanels();
        getContentPane().add(cardManager.getCardPane());
        setIconifiable(true);
        setSize(Components.getRelativeSize(.65,.55));
    }

    public ArticleWindow(int conferenceID, boolean isThirdPartyConference) {
        this.conferenceID = conferenceID;
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        linkPanels(isThirdPartyConference);
        getContentPane().add(cardManager.getCardPane());
        setIconifiable(true);
        setSize(Components.getRelativeSize(.65,.55));
    }

    private void linkPanels()
    {   
        ListArticlesPanel ListPanel = new ListArticlesPanel(cardManager, conferenceID);
        RegisterArticlePanel registerPanel = new RegisterArticlePanel( cardManager, conferenceID); 
        
        GeneralRepository.getArticleService().
                addObserver((Observer) ListPanel);
        ((Observer) ListPanel).update();
        
        cardManager.addPanel(ListPanel, "listPanel");
        cardManager.addPanel(registerPanel, "registerPanel");
    }

    private void linkPanels(boolean isThirdPartyConference) {
        ListArticlesPanel ListPanel = new ListArticlesPanel(cardManager, conferenceID, isThirdPartyConference);
        RegisterArticlePanel registerPanel = new RegisterArticlePanel(cardManager, conferenceID);
        
        GeneralRepository.getArticleService().
                addObserver((Observer) ListPanel);
        ((Observer) ListPanel).update();
        
        cardManager.addPanel(ListPanel, "listPanel");
        cardManager.addPanel(registerPanel, "registerPanel");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
