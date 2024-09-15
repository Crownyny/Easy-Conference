/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.models.Author;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.Utilities;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Default
 */
public class RegisterArticleWindow extends RegisterWindow {
    
    private final StorageService<Article> objStorageService;
    private final StorageService<Author> authors; 

    /**
     * Creates new form VtnListarArticulos
     * @param objStorageService
     */
    public RegisterArticleWindow (StorageService<Article> objStorageService) {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombre:", new FieldConfig(new JTextField(20)));
        inputFields.put("Revista:", new FieldConfig(new JTextField(20)));
        inputFields.put("", new FieldConfig(new JButton("Asignar autor")));
        super(new JLabel("Registrar Articulo"), inputFields);
        MemoryArrayListRepository<Author> authorRepository = new MemoryArrayListRepository<>();
        authors = new StorageService<>(authorRepository);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void registerAction() {
        ArrayList<String> values = new ArrayList<>();
        fieldConfigs.values().stream()
            .map(FieldConfig::getFieldType)
            .filter(JTextField.class::isInstance)
            .map(JTextField.class::cast)
            .map(JTextField::getText)
            .forEach(values::add);
        
 
        Article article = new Article(values.get(0),authors.listAll(), values.get(1));

        if (objStorageService.store(article))
            Utilities.successMessage("El registro del articulo fue exitoso", "Registro exitoso");
        else
            Utilities.successMessage("El registro del articulo no se realizo", "Error en el registro");

    }

    @Override
    protected void extraButtonAction() {
        ListAuthorWindow objAuthorWindow =
            new ListAuthorWindow(this.authors);
        objAuthorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        objAuthorWindow.setVisible(true);     
        System.out.println("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
