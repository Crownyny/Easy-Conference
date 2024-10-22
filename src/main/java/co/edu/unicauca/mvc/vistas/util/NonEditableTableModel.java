package co.edu.unicauca.mvc.vistas.util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {

    private final List<Integer> editableColumns;

    public NonEditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        editableColumns = new ArrayList<>();
    }

    public void addEditableColumn(int column) {
        editableColumns.add(column);
    }

    @Override
    public boolean isCellEditable(int row, int column) 
    {
    // Si la columna esta en la lista de columnas editables, se puede editar
        return editableColumns.contains(column); 
    }
}