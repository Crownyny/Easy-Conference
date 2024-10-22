package co.edu.unicauca.mvc.vistas.windows;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.vistas.conferencePanels.ListConferencesPanel;
import co.edu.unicauca.mvc.vistas.conferencePanels.RegisterConferencePanel;
import co.edu.unicauca.mvc.vistas.mainWindowPanels.MainPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Default
 */
public class ConferenceWindow extends JInternalFrame {
    private final CardPanelManager cardManager;
    private final int userID;

   public ConferenceWindow(MainPanel adminWindow, int userID) {
        this.userID = userID;
        setLayout(new BorderLayout());
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        linkPanels(adminWindow, "Mis Conferencias");
        add(cardManager.getCardPane(), BorderLayout.CENTER);
        setIconifiable(true);
        setSize(Elements.getRelativeSize(.55,.65));
    }

    
   public ConferenceWindow(MainPanel adminWindow, int userID, boolean flag) {
        this.userID = userID;
        setLayout(new BorderLayout());
        cardManager = new CardPanelManager(new JPanel(new CardLayout()));
        linkPanels(adminWindow, "Conferencias Disponibles", flag);
        add(cardManager.getCardPane(), BorderLayout.CENTER);
        setClosable(true);
        setIconifiable(true);
        setSize(Elements.getRelativeSize(.55,.65));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void linkPanels(MainPanel adminWindow,String titleConference)
    {   
        ListConferencesPanel ListPanel = new ListConferencesPanel(adminWindow, cardManager, userID, titleConference);
        RegisterConferencePanel registerPanel = new RegisterConferencePanel( cardManager, userID);
        
        GeneralRepository.getConferenceService().
                addObserver((Observer) ListPanel);
        ((Observer) ListPanel).update();
        
        cardManager.addPanel(ListPanel, "listPanel");
        cardManager.addPanel(registerPanel, "registerPanel");
        cardManager.showPanel("listPanel");
    }

    private void linkPanels(MainPanel adminWindow,String titleConference, boolean flag)
    {   
        ListConferencesPanel ListPanel = new ListConferencesPanel(adminWindow, cardManager, userID, titleConference, flag);
        RegisterConferencePanel registerPanel = new RegisterConferencePanel( cardManager, userID);

          GeneralRepository.getConferenceService().
                addObserver((Observer) ListPanel);
        ((Observer) ListPanel).update();
        
        cardManager.addPanel(ListPanel, "listPanel");
        cardManager.addPanel(registerPanel, "registerPanel");
        cardManager.showPanel("listPanel"); 
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
