package co.edu.unicauca.mvc.vistas.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * Custom ButtonEditor for handling button clicks in a table cell.
 */
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private int row;
    private ButtonClickListener buttonClickListener;  // Custom listener

    public ButtonEditor(JCheckBox checkBox, ButtonClickListener listener) {
        super(checkBox);
        this.buttonClickListener = listener;  // Assign the custom listener
        button = new JButton();
        button.setOpaque(true);

        // Define action for the button
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();  // Stop editing the button
            if (buttonClickListener != null) {
                buttonClickListener.onClick(row);  // Call the custom listener
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;  // Save the current row
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