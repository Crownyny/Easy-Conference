package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.ArticleManagementService;
import co.edu.unicauca.mvc.controllers.StorageService;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import co.edu.unicauca.mvc.models.Article;
import java.util.stream.Collectors;

/**
 *
 * @author Default
 */
public class ListArticlesWindow extends ListWindow {
    
    private final StorageService<ArticleManagementService> objStorageService;

    /**
     * Creates new form VtnListarArticulos
     * @param objStorageService
     */
    public ListArticlesWindow(StorageService<ArticleManagementService> objStorageService) {
        super("Listado de Articulos", "Registrar Articulos", new String[]{"Nombre", "Revista"});
        this.objStorageService = objStorageService;
        System.out.println("ENTRAAAAA");
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
        ArrayList<Article> articleList = objStorageService.listAll().stream()
            .map(ArticleManagementService::getArticle)
            .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < articleList.size(); i++) {
            String[] row = { 
                articleList.get(i).getTitle(),  
                articleList.get(i).getJournal()
            };
            model.addRow(row);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
