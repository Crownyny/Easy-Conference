package co.edu.unicauca.mvc.vistas.authorPanels;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListAuthorsPanel extends ListPanel{
    private final CardPanelManager cardManager;
    private final StorageService<Author> tempAuthors;

    public ListAuthorsPanel(CardPanelManager cardManager, StorageService<Author> tempAuthors) {
        super("Listado de autores", "Registrar autor", new String[]{"Nombre", "Apellido", "Email", "Tipo de autor"});
        this.tempAuthors = tempAuthors;
        this.cardManager = cardManager;
    } 
    
    @Override
    protected void registerAction() {
        cardManager.showPanel("registerArticlePanel");
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int rows = this.table.getRowCount();
        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }        
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        clearTable();
        ArrayList<Author> authorList = (ArrayList<Author>) tempAuthors.listAll();// Fix show all 

        for (Author author : authorList) {
            String[] row = { 
                author.getFirstName(),
                author.getLastName(),
                author.getMail()
            };
            model.addRow(row);
        }
    }

    @Override
    public void update() {
        fillTable();
    }
}
