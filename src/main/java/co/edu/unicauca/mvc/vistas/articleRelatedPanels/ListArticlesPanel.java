package co.edu.unicauca.mvc.vistas.articleRelatedPanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.vistas.evaluatorPanel.ListEvaluatorPanel;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.util.ButtonClickListener;
import co.edu.unicauca.mvc.vistas.util.ButtonEditor;
import co.edu.unicauca.mvc.vistas.util.ButtonRenderer;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class ListArticlesPanel extends ListPanel{
    private final CardPanelManager cardManager;
    private final int conferenceID;


    public ListArticlesPanel(CardPanelManager cardManager, int conferenceID) 
    {
        super("Listado de Articulos", "Registrar Articulos", new String[]{"Nombre", "Revista","Cantidad de autores","Asignar evaluador"});
        this.conferenceID = conferenceID;
        this.cardManager = cardManager;
    }
    

    @Override
    protected void registerAction() {
         cardManager.showPanel("registerPanel");
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
        ArrayList<Article> articleList = (ArrayList<Article>) GeneralRepository.getArticlesByConferenceId(conferenceID);
        
        for (int i = 0; i < articleList.size(); i++) 
        {
            String[] row = { 
                articleList.get(i).getTitle(),  
                articleList.get(i).getJournal(),
                GeneralRepository.getArticleLinkServiceById(articleList.get(i).getId()).getAuthors().size() + "", 
                "Seleccionar"
            };
            model.addRow(row);
        }
        
        ButtonClickListener listener = (int row) -> {
            Article selectedArticle = articleList.get(row);
            ListEvaluatorPanel listEvaluatorPanel = new ListEvaluatorPanel(cardManager, selectedArticle.getId()); 
            cardManager.addPanel(listEvaluatorPanel,"listEvaluatorPanel");
                    
            GeneralRepository.getEvaluatorService().
                    addObserver((Observer) listEvaluatorPanel);
            ((Observer) listEvaluatorPanel).update();
            cardManager.showPanel("listEvaluatorPanel");
        };

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), listener));
    }
    

    @Override
    public void update() 
    {
        fillTable();
    }
}
