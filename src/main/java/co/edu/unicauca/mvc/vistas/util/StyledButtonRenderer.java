package co.edu.unicauca.mvc.vistas.util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButtonRenderer extends JButton implements TableCellRenderer {

    public StyledButtonRenderer()
    {
        setBackground(new Color(0x2c4464));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setOpaque(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        setBackground(new Color(0x2c4464));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setOpaque(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
        return this;
    }


}