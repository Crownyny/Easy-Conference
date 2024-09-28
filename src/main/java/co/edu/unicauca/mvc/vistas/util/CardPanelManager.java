package co.edu.unicauca.mvc.vistas.util;

import java.awt.CardLayout;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CardPanelManager {

    private final JPanel cardPane;
    private final CardLayout cardLayout;
    private final Map<String, JPanel> panelMap; 

    public CardPanelManager(JPanel cardPane) {
        this.cardPane = cardPane;
        this.cardLayout = (CardLayout) cardPane.getLayout();
        this.panelMap = new HashMap<>(); 
    }

    public void addPanel(JPanel panel, String name) {
        cardPane.add(panel, name);
        panelMap.put(name, panel); 
    }

    public void showPanel(String name) {
        cardLayout.show(cardPane, name);
        

    }

    public JPanel getCardPane() {
        return cardPane;
    }

    public JPanel getPanel(String name) {
        return panelMap.get(name); 
    }
}
