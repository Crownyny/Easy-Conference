package co.edu.unicauca.mvc.vistas.conferencePanels;

import co.edu.unicauca.mvc.utilities.*;
import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Conference;
import co.edu.unicauca.mvc.models.Organizer;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.mainWindowPanels.MainPanel;
import co.edu.unicauca.mvc.vistas.util.ButtonClickListener;
import co.edu.unicauca.mvc.vistas.util.ButtonEditor;
import co.edu.unicauca.mvc.vistas.util.ButtonRenderer;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ListConferencesPanel extends ListPanel{
    private final CardPanelManager cardManager;
    private final int userID;
    private final  MainPanel adminWindow;
    private boolean flag = false;
    
    public ListConferencesPanel(MainPanel adminWindow, CardPanelManager cardManager,
            int userID, String title) {
        super(title, "Registrar Conferencias", 
                new String[]{"Nombre", "Fecha Inicio", "Fecha Fin", "Costo", "Ubicacion","Temas", ""},false);
        this.adminWindow = adminWindow;
        this.userID =userID;
        this.cardManager = cardManager;
    }
    
    public ListConferencesPanel(MainPanel adminWindow, CardPanelManager cardManager, int userID, String title, boolean flag) {
        super(title, "Registrar Conferencias", 
                new String[]{"Nombre", "Fecha Inicio", "Fecha Fin", "Costo", "Ubicacion","Temas", ""},false);
        this.adminWindow = adminWindow;
        this.userID =userID;
        this.flag = flag;
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
        ArrayList<Conference> conferenceList;
        if(flag)
           conferenceList = (ArrayList) GeneralRepository.getConferenceService().listAll();
        else 
            conferenceList = (ArrayList) GeneralRepository.getConferencesByUserId(userID);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < conferenceList.size(); i++) {
            String[] row = {
                conferenceList.get(i).getName(), 
                formatter.format(conferenceList.get(i).getStartDate()), 
                formatter.format(conferenceList.get(i).getEndDate()), 
                conferenceList.get(i).getRegistrationCost() + "",
                conferenceList.get(i).getLocation(),
                conferenceList.get(i).topicsToString(), 
                "Seleccionar"
            };
            model.addRow(row);
        }
        
        ButtonClickListener listener = (int row) -> {
            Conference selectedConference = conferenceList.get(row);
            
            adminWindow.associateService(Organizer.class, selectedConference.getId());
            adminWindow.associateService(Article.class, selectedConference.getId());
            if(flag)
                adminWindow.getCardManager().showPanel("otherConferencePanel");
            else
                adminWindow.getCardManager().showPanel("myConferencePanel");

            adminWindow.setVisibility(MainPanel.VisibilityState.NONE);
            

            setVisible(false);
        };
        
        // Cargar la imagen desde los recursos
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/abajo.png"));

        // Obtener la altura de la fila para escalar la imagen del encabezado
        int rowHeight = table.getRowHeight();
    
        // Aplicar el HeaderRendererWithIcon al encabezado de la columna correspondiente
        JTableHeader header = table.getTableHeader();
        header.getColumnModel().getColumn(6).setHeaderRenderer(new HeaderRendererWithIcon(icon, rowHeight));

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), listener));

    }

    @Override
    public void update() {
        fillTable();
    }

    @Override
    protected void returnAction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
