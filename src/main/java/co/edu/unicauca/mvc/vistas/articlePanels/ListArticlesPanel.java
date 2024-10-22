package co.edu.unicauca.mvc.vistas.articlePanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.vistas.evaluatorPanel.ListEvaluatorPanel;
import co.edu.unicauca.mvc.vistas.evaluatorPanel.RegisterEvaluatorPanel;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.util.ButtonClickListener;
import co.edu.unicauca.mvc.vistas.util.ButtonEditor;
import co.edu.unicauca.mvc.vistas.util.ButtonRenderer;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class ListArticlesPanel extends ListPanel{
    private final CardPanelManager cardManager;
    private final int conferenceID;
    private boolean isThirdPartyConference;


    public ListArticlesPanel(CardPanelManager cardManager, int conferenceID) 
    {
        super("Listado de Articulos", "Registrar Articulos", new String[]{"Nombre", "Revista","Abstract","Palabras claves","Cantidad de autores","Asignar evaluador"},false,true);
        this.conferenceID = conferenceID;
        this.cardManager = cardManager;
    }
    
    public ListArticlesPanel(CardPanelManager cardManager, int conferenceID, boolean isThirdPartyConference) 
    {
        super("Listado de Articulos", "Registrar Articulos", new String[]{"Nombre", "Revista","Abstract","Palabras claves","Cantidad de autores","Asignar evaluador"},false,true);
        this.conferenceID = conferenceID;
        this.cardManager = cardManager;
        this.isThirdPartyConference = isThirdPartyConference;
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
            List<String> row = new ArrayList<>();
            row.add(articleList.get(i).getTitle());
            row.add(articleList.get(i).getJournal());
            row.add(articleList.get(i).getAbstract());
            row.add(articleList.get(i).getKeywords());
            row.add(String.valueOf(GeneralRepository.getArticleLinkServiceById(articleList.get(i).getId()).getAuthors().size()));
            
            if (!isThirdPartyConference) {
                row.add("Seleccionar");
            }
            model.addRow(row.toArray());
        }

        if(isThirdPartyConference)
            return;

        ButtonClickListener listener = (int row) -> {
            Article selectedArticle = articleList.get(row);
            RegisterEvaluatorPanel registerEvaluatorPanel = new RegisterEvaluatorPanel(cardManager, selectedArticle.getId()); 
            ListEvaluatorPanel listEvaluatorPanel = new ListEvaluatorPanel(cardManager, selectedArticle.getId()); 
            cardManager.addPanel(listEvaluatorPanel,"listEvaluatorPanel");
            cardManager.addPanel(registerEvaluatorPanel,"registerEvaluatorPanel");
                    
            GeneralRepository.getEvaluatorService().
                    addObserver((Observer) listEvaluatorPanel);
            ((Observer) listEvaluatorPanel).update();
            cardManager.showPanel("listEvaluatorPanel");
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), listener));
    }
    

    @Override
    public void update() 
    {
        fillTable();
    }

    @Override
    protected void returnAction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
