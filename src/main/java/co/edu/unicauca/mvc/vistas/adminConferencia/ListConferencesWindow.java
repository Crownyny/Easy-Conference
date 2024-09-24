package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.ArticleManagementService;
import co.edu.unicauca.mvc.controllers.ConferenceManagementService;
import co.edu.unicauca.mvc.controllers.StorageService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import co.edu.unicauca.mvc.models.Conference;
import co.edu.unicauca.mvc.models.Organizer;
import co.edu.unicauca.mvc.vistas.panels.MainPanel;
import co.edu.unicauca.mvc.vistas.util.ButtonClickListener;
import co.edu.unicauca.mvc.vistas.util.ButtonEditor;
import co.edu.unicauca.mvc.vistas.util.ButtonRenderer;
import java.util.stream.Collectors;
import javax.swing.JCheckBox;

/**
 *
 * @author Default
 */
public class ListConferencesWindow extends ListWindow {

    private final StorageService<ConferenceManagementService> objStorageService;
    private final  MainPanel adminWindow;

    /**
     * Creates new form VtnListarArticulos
     * @param adminWindow
     * @param objStorageService
     */
    public ListConferencesWindow(MainPanel adminWindow, StorageService<ConferenceManagementService> objStorageService) {
        super("Listado de Conferencias", "Registrar Conferencias", 
                new String[]{"Nombre", "Fecha Inicio", "Fecha Fin", "Costo", "Ubicacion","Temas", "Ingresar"});
        this.objStorageService=objStorageService;
        this.adminWindow = adminWindow;
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
    
    @Override 
    protected void updateAction() {                                                 
        fillTable();
    }   

    @Override
    protected void registerAction(){
        RegisterConferenceWindow registerConferenceWindow =
        new RegisterConferenceWindow(objStorageService);
        registerConferenceWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerConferenceWindow.setVisible(true);       
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
        ArrayList<Conference> conferenceList = objStorageService.listAll().stream()
            .map(ConferenceManagementService::getConference)
            .collect(Collectors.toCollection(ArrayList::new));
        
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
            ConferenceManagementService conferenceManager = objStorageService.listAll().stream()
                        .filter(conferenceMngr -> conferenceMngr.getConference().equals(selectedConference))
                        .findFirst()
                        .orElse(null);

            adminWindow.associateService(Organizer.class, conferenceManager.getOrganizerService());
            adminWindow.associateService(ArticleManagementService.class, conferenceManager.getArticleService());
            adminWindow.getCardManager().showPanel("conferencePanel");
            setVisible(false);
        };

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), listener));

    }
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
