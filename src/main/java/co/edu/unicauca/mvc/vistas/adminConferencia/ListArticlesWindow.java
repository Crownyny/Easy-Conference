package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.ArticleManagementService;
import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.vistas.util.ButtonClickListener;
import co.edu.unicauca.mvc.vistas.util.ButtonEditor;
import co.edu.unicauca.mvc.vistas.util.ButtonRenderer;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class ListArticlesWindow extends ListWindow {
    
    private final StorageService<ArticleManagementService> objStorageService;

    /**
     * Creates new form VtnListarArticulos
     * @param adminWindow
     * @param objStorageService
     */
    public ListArticlesWindow( StorageService<ArticleManagementService> objStorageService) {
        super("Listado de Articulos", "Registrar Articulos", new String[]{"Nombre", "Revista","Cantidad de autores","Asignar evaluador"});
        this.objStorageService = objStorageService;
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
    protected void registerAction() {
        RegisterArticleWindow objVtnRegisterArticle =
            new RegisterArticleWindow(this.objStorageService);
        objVtnRegisterArticle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        objVtnRegisterArticle.setVisible(true);       
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
        ArrayList<ArticleManagementService> articleList = (ArrayList<ArticleManagementService>) objStorageService.listAll();
        

        for (int i = 0; i < articleList.size(); i++) 
        {
            String[] row = { 
                articleList.get(i).getArticle().getTitle(),  
                articleList.get(i).getArticle().getJournal(),
                articleList.get(i).getAuthorService().listAll().size()+"",
                "Seleccionar"
            };
            model.addRow(row);
        }
        
        ButtonClickListener listener = (int row) -> {
            ArticleManagementService selectedArticleManager = articleList.get(row);

            ListEvaluatorWindow objEvaluatorWindow =
                new ListEvaluatorWindow(selectedArticleManager.getEvaluatorService());
            selectedArticleManager.getEvaluatorService().addObserver(objEvaluatorWindow);
            objEvaluatorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            objEvaluatorWindow.setVisible(true);     
        };

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), listener));
    }
    

    @Override
    public void update(Object o) 
    {
        fillTable();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
