package co.edu.unicauca.mvc.vistas.organizerPanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Organizer;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListOrganizersPanel extends ListPanel{
    private final int conferenceID;
    private final CardPanelManager cardManager;

    public ListOrganizersPanel (CardPanelManager cardManager, int conferenceID) {
        super("Listado de Organizadores", "Registrar Organizadores", new String[]{"Nombres", "Apellidos", "Universidad"});
        this.conferenceID =conferenceID;
        this.cardManager = cardManager;
    }
    
    @Override
    protected void registerAction(){
        cardManager.showPanel("registerPanel");
    }

    public void clearTable(){

        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int rows = this.table.getRowCount();
        for (int i = 0; rows > i; i++) {
            model.removeRow(0);
        }        
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        clearTable();
        ArrayList<Organizer> organizerList = (ArrayList<Organizer>) GeneralRepository.getOrganizersByConferenceId(conferenceID);
        for (int i = 0; i < organizerList.size(); i++) {
            String[] row = { 
                organizerList.get(i).getFirstName(), 
                organizerList.get(i).getLastName(), 
                organizerList.get(i).getUniversity() 
            };
            model.addRow(row);
        }
        
    }

    @Override
    public void update() {
        fillTable();
    }
}
