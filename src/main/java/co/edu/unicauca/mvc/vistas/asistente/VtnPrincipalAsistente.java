package co.edu.unicauca.mvc.vistas.asistente;


import co.edu.unicauca.mvc.infrastructure.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author HSVSTT2
 */
public class VtnPrincipalAsistente extends javax.swing.JFrame implements Observer{
    private final  JTextArea notificationsArea = new JTextArea();
    /**
     * Creates new form VtnPrincipalAdmin
     */
    public VtnPrincipalAsistente() {
        showGui();
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
            .addGap(0, 526, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void showGui()
    {
        setLayout(new BorderLayout());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int panelHeight = (int) (screenHeight*0.6);
        int panelWidth = (int) (screenWidth*0.55);
        int panelNorthHeight = (int) (panelHeight * 0.1);
        int panelCenterHeight = (int) (panelHeight * 0.85);
        int panelSouthHeight = (int) (panelHeight * 0.15);
        
        this.setSize(panelWidth,panelHeight);

        JPanel panelNorth = new JPanel(new GridBagLayout());
        panelNorth.setPreferredSize(new Dimension(panelWidth, panelNorthHeight));
        
        int titleFontsize =  Math.min(panelWidth, panelHeight) / 25;
        JLabel titleLabel = new JLabel("Notificaciones Asistente");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, titleFontsize)); 
        panelNorth.add(titleLabel);
        panelNorth.setBackground(new Color(0x3c647c));
        
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setPreferredSize(new Dimension(panelWidth, panelCenterHeight));
        panelCenter.setBackground(new Color(0xD7EAF9));

        notificationsArea.setLineWrap(true);
        notificationsArea.setWrapStyleWord(true);
        notificationsArea.setEditable(false);
        notificationsArea.setHighlighter(null);
        notificationsArea.setCaretColor(new Color(0, 0, 0, 0));

        JScrollPane scrollPane = new JScrollPane(notificationsArea);
        scrollPane.setPreferredSize(new Dimension(panelWidth, panelCenterHeight));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;   
        gbc.gridy = 0; 
        gbc.gridwidth = 1; 
        gbc.gridheight = 1; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0;  
        gbc.fill = GridBagConstraints.BOTH;  

        panelCenter.add(scrollPane, gbc);

        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(new Color(0x7F818F));
        panelSouth.setPreferredSize(new Dimension(panelWidth, panelSouthHeight));
        
        this.getContentPane().add(panelSouth, BorderLayout.SOUTH);           
        this.getContentPane().add(panelCenter, BorderLayout.CENTER);
        this.getContentPane().add(panelNorth, BorderLayout.NORTH);
    }

    private void addNotification(String notificacion) {
        if (!notificationsArea.getText().isEmpty()) {
            notificationsArea.append("\n" + notificacion);
        } else {
            notificationsArea.setText(notificacion);
        }
    }

    

    @Override
    public void update(Object o) {

    }

}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

