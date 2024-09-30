package co.edu.unicauca.mvc.vistas.evaluatorPanel;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.Evaluator;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListEvaluatorPanel extends ListPanel{
    private final CardPanelManager cardManager;
    private final int articleID;


    public ListEvaluatorPanel(CardPanelManager cardManager, int articleID) {
        super("Listado de evaluadores", "Registrar evaluadores", new String[]{"Nombre", "Apellido", "Email", "Institucion asociada"},true,true);
        this.articleID = articleID;
        this.cardManager = cardManager;
    }
    @Override
    protected void registerAction() {
        cardManager.showPanel("registerEvaluatorPanel");
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
        ArrayList<Evaluator> evaluatorList = (ArrayList<Evaluator>) GeneralRepository.getEvaluatorsByArticleId(articleID);

        for (Evaluator evaluator : evaluatorList) {
            String[] row = { 
                evaluator.getFirstName(),
                evaluator.getLastName(),
                evaluator.getMail(),
                evaluator.getAfiliation()
            };
            model.addRow(row);
        }
       
    }

    @Override
    public void update() {
        fillTable();
    }

    @Override
    protected void returnAction() {
       cardManager.showPanel("listPanel");
    }

}
