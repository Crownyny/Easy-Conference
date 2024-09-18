package co.edu.unicauca.mvc.vistas.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Default
 */
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private int row;
    private ButtonClickListener buttonClickListener;  // Listener personalizado
    
    public ButtonEditor(JCheckBox checkBox, ButtonClickListener listener) {
        super(checkBox);
        this.buttonClickListener = listener;  // Asigna el listener personalizado
        button = new JButton();
        button.setOpaque(true);

        // Definir acción para el botón
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();  // Finaliza la edición del botón
            if (buttonClickListener != null) {
                buttonClickListener.onClick(row);  // Llama al listener personalizado
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;  // Guardamos la fila actual
        label = (value == null) ? "View" : value.toString();
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }
}
