/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.vistas.genericPanels;

import co.edu.unicauca.mvc.infrastructure.Observer;
import co.edu.unicauca.mvc.utilities.Elements;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


public abstract class ListPanel extends JPanel implements Observer{
    protected JLabel titleLabel;
    protected String registrarButtonText;
    protected String[] columnNames;
    protected JTable table;

    /**
     * Creates new form VtnListarPlantilla
     * @param titleLabel
     * @param registrarButtonText
     * @param columnNames
     */
    public ListPanel(String titleLabel, String registrarButtonText, String[] columnNames) 
    {
        this.titleLabel = new JLabel(titleLabel);
        this.registrarButtonText = registrarButtonText;
        this.columnNames = columnNames;
        Object[][] data ={};
        this.table = new JTable();
        this.table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        showGui();

    }
    
    public ListPanel(String[] columnNames) 
    {
        this.titleLabel = new JLabel();
        this.registrarButtonText = "";
        showGui();
    }
    
       private void showGui() {
        // Set up the window size
        setLayout(new BorderLayout());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int panelHeight = (int) (screenHeight * 0.6);
        int panelWidth = (int) (screenWidth * 0.55);
        int panelNorthHeight = (int) (panelHeight * 0.1);
        int panelCenterHeight = (int) (panelHeight * 0.85);
        int panelSouthHeight = (int) (panelHeight * 0.15);
        
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelNorth = new JPanel(new GridBagLayout());
        panelNorth.setPreferredSize(new Dimension(panelWidth, panelNorthHeight));

        int titleFontSize = Math.min(panelWidth, panelHeight) / 25;
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, titleFontSize)); 
        panelNorth.add(titleLabel);
        panelNorth.setBackground(new Color(0x3c647c));

        JPanel panelCenter = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        int buttonFontSize = Math.min(panelWidth, panelHeight) / 30;

        try {
            BufferedImage iconRegister = ImageIO.read(getClass().getResource("/resources/pen-drawing.png"));
            // Resize the images to font size
            Image iconRegisterScaled = iconRegister.getScaledInstance(buttonFontSize, buttonFontSize, Image.SCALE_SMOOTH);

            JButton registerButton = Elements.addButton(new JButton(" Registrar"), buttonFontSize);
            registerButton.setIcon(new ImageIcon(iconRegisterScaled)); 
            gbc.gridx = 1; // Column 1
            gbc.gridy = 0; // Row 0
            gbc.gridwidth = 1; // Occupies 1 column
            gbc.weightx = 1; // Half of the horizontal space  
            registerButton.addActionListener(e -> registerAction());    
            panelCenter.add(registerButton, gbc);

        } catch (IOException e) {}

        // Customize the header renderer
        JTableHeader header = this.table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(0x3c647c)); 
                label.setForeground(Color.WHITE); 
                label.setFont(new Font("Leelawadee UI", Font.BOLD, Math.min(panelWidth, panelHeight) / 35)); // Font size
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        // Customize cell renderer for centering text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply centered renderer to all columns in the table
        for (int i = 0; i < this.table.getColumnCount(); i++) {
            this.table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        gbc.gridwidth = 2; // Occupies 2 columns
        gbc.weightx = 1.0; // Horizontal space distribution
        gbc.weighty = 1.0; // Vertical space distribution
        gbc.fill = GridBagConstraints.BOTH; // Table takes up all space
        panelCenter.add(tableScrollPane, gbc);
        panelCenter.setBackground(new Color(0xD7EAF9));
        panelCenter.setPreferredSize(new Dimension(panelWidth, panelCenterHeight));

        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(new Color(0x7F818F));
        panelSouth.setPreferredSize(new Dimension(panelWidth, panelSouthHeight));

        add(panelSouth, BorderLayout.SOUTH);           
        add(panelCenter, BorderLayout.CENTER);
        add(panelNorth, BorderLayout.NORTH);

    }

    protected abstract void registerAction();
}
