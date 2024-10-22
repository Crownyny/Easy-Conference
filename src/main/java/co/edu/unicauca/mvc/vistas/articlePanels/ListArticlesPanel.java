package co.edu.unicauca.mvc.vistas.articlePanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.models.Article;
import co.edu.unicauca.mvc.utilities.Components;
import co.edu.unicauca.mvc.vistas.evaluatorPanel.ListEvaluatorPanel;
import co.edu.unicauca.mvc.vistas.evaluatorPanel.RegisterEvaluatorPanel;
import co.edu.unicauca.mvc.vistas.genericPanels.ListPanel;
import co.edu.unicauca.mvc.vistas.util.ButtonClickListener;
import co.edu.unicauca.mvc.vistas.util.ButtonEditor;
import co.edu.unicauca.mvc.vistas.util.ButtonRenderer;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import co.edu.unicauca.mvc.vistas.util.NonEditableTableModel;

import java.util.ArrayList;
import java.util.List;
import co.edu.unicauca.mvc.vistas.util.StyledButtonRenderer;

import javax.swing.JButton;
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
        NonEditableTableModel model = (NonEditableTableModel) this.table.getModel();
        int rows = this.table.getRowCount();
        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }        
    }

    private void fillTable() {
        // Definir la columna donde estará el botón
        final int BUTTONCOLUMN = 5;
    
        // Obtener el modelo de tabla y asegurarse de que no sea editable por defecto
        NonEditableTableModel model = (NonEditableTableModel) this.table.getModel();
    
        // Limpiar la tabla antes de llenarla
        clearTable();
    
        // Obtener la lista de artículos de la conferencia utilizando el ID de la conferencia
        ArrayList<Article> articleList = (ArrayList<Article>) GeneralRepository.getArticlesByConferenceId(conferenceID);
    
        // Formatear la tabla con la información de los artículos
        for (int i = 0; i < articleList.size(); i++) {
            // Crear una fila con los datos del artículo actual
            List<String> row = new ArrayList<>();
            row.add(articleList.get(i).getTitle());  // Título del artículo
            row.add(articleList.get(i).getJournal());  // Revista del artículo
            row.add(articleList.get(i).getAbstract());  // Resumen del artículo
            row.add(articleList.get(i).getKeywords());  // Palabras clave
            row.add(String.valueOf(
                GeneralRepository.getArticleLinkServiceById(articleList.get(i).getId()).getAuthors().size()));  // Número de autores
    
            // Si no es una conferencia de terceros, añadir el botón 'Seleccionar'
            if (!isThirdPartyConference) {
                row.add("Seleccionar");
            }
    
            // Agregar la fila al modelo de la tabla
            model.addRow(row.toArray());
        }
    
        // Si es una conferencia de terceros, no se añade funcionalidad para botones
        if (isThirdPartyConference)
            return;
    
        // Especificar que la columna del botón será editable
        model.addEditableColumn(BUTTONCOLUMN);
    
        // Crear el listener que define la acción al hacer clic en el botón de la columna
        ButtonClickListener listener = (int row) -> {
            // Obtener el artículo seleccionado
            Article selectedArticle = articleList.get(row);
    
            // Crear paneles relacionados con evaluadores
            RegisterEvaluatorPanel registerEvaluatorPanel = new RegisterEvaluatorPanel(cardManager, selectedArticle.getId());
            ListEvaluatorPanel listEvaluatorPanel = new ListEvaluatorPanel(cardManager, selectedArticle.getId());
    
            // Agregar los paneles al administrador de tarjetas (cardManager)
            cardManager.addPanel(listEvaluatorPanel, "listEvaluatorPanel");
            cardManager.addPanel(registerEvaluatorPanel, "registerEvaluatorPanel");
    
            // Registrar al panel de lista de evaluadores como observador del servicio de evaluadores
            GeneralRepository.getEvaluatorService().addObserver((Observer) listEvaluatorPanel);
    
            // Actualizar la información del panel
            ((Observer) listEvaluatorPanel).update();
    
            // Mostrar el panel de lista de evaluadores
            cardManager.showPanel("listEvaluatorPanel");
        };
    
        // Configurar el renderizador y editor del botón en la columna especificada
        table.getColumnModel().getColumn(BUTTONCOLUMN).setCellRenderer(new StyledButtonRenderer());
        table.getColumnModel().getColumn(BUTTONCOLUMN).setCellEditor(new ButtonEditor(new JCheckBox(), listener));
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
