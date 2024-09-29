package co.edu.unicauca.mvc.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class HeaderRendererWithIcon extends DefaultTableCellRenderer {
    private final ImageIcon icon;

    public HeaderRendererWithIcon(ImageIcon icon, int rowHeight) {
        Image scaledIcon = icon.getImage().getScaledInstance(rowHeight, rowHeight, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(scaledIcon);
        setHorizontalAlignment(CENTER); // Centrar el contenido en el encabezado
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Crear un JLabel que contiene el texto y la imagen
        JLabel headerLabel = new JLabel(value.toString(), icon, JLabel.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.RIGHT); // Posicionar el texto a la derecha de la imagen
        headerLabel.setVerticalTextPosition(SwingConstants.CENTER);  // Centrar verticalmente el texto e imagen

        headerLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 14)); // Aplicar fuente personalizada
        headerLabel.setForeground(Color.WHITE); // Cambiar el color del texto
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(0x3c647c)); // Fondo azul

        return headerLabel;
    }
}
