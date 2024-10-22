package co.edu.unicauca.mvc.vistas.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.unicauca.mvc.utilities.Components;
import co.edu.unicauca.mvc.vistas.windows.PopUpWindow.PopUpType;

/*
 * Generic pop-up window
 * This class is used to create a generic pop-up window that can be used to display messages
 * Based on the JDialog class from the Swing library
 */
public class PopUpWindow extends JDialog {
    private final PopUpType operation;
    private final String message;
    /**
     * Constructor
     * @param parent Parent frame
     * @param operation Type of operation (WARNING, ERROR, SUCCESS)
     * @param message Message to display
     */
    public PopUpWindow(JFrame parent,PopUpType operation, String message) {
        super(parent, true);
        this.operation = operation;
        this.message = message;
        setWindow();
    }

    /*
     * PopUpType
     * This enum is used to define the type of pop-up window
     * It has three types: WARNING, ERROR, and SUCCESS
     * Each type has a different color
     * The color is used to set the foreground color of the pop-up window title
     */
    public static enum PopUpType {
        WARNING(new Color(0xfbff3b)),
        ERROR(new Color(0xff0100)),
        SUCCESS(new Color(0x73c360)),;

        private final Color color;

        PopUpType(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

    }
    /**
     * Method to set the window properties
     */
    private void setWindow() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close the window when the user clicks the close button
        setResizable(false); // Disable window resizing
        setSize(400, 200); // Set the window size
        setLocationRelativeTo(null); // Center the window
        setUndecorated(true); // Make the window unable to move

        // Create a panel to hold the components
        JPanel mainPanel = new JPanel(new BorderLayout()); // Create a panel with BorderLayout
        mainPanel.setBackground(Components.BUTTONCOLOR); // Set the background color

        // Create the close label ("X")
        JLabel closeLabel = new JLabel("x", JLabel.CENTER); // Create the label with "X" centered
        closeLabel.setOpaque(true); // Make the label opaque to show background
        closeLabel.setBackground(new Color(0xC62E2E)); // Set the background color to red
        closeLabel.setForeground(Color.WHITE); // Set the text color to white
        closeLabel.setPreferredSize(new Dimension(20, 20)); // Set the size to a square 
        Font labelFont = new Font("Lucida Console", Font.PLAIN, 16); // Set the font
        closeLabel.setFont(labelFont); // Apply the font

        // Add a MouseListener to detect hover and click events
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Increase font size and change background on hover
                closeLabel.setFont(labelFont.deriveFont(Font.BOLD)); // Increase font size
                closeLabel.revalidate(); // Recalculate the layout
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restore original font size when not hovered
                closeLabel.setFont(labelFont.deriveFont(Font.PLAIN)); // Restore font size
                closeLabel.revalidate(); // Recalculate the layout
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Close the window when clicked
                dispose();
            }
        });

        JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Use FlowLayout to align right
        closePanel.setBackground(Components.BUTTONCOLOR); // Set the background color of the panel
        closePanel.add(closeLabel); // Add the label to the panel

        mainPanel.add(closePanel, BorderLayout.NORTH); // Add the panel to the main panel at the top

        // Create the first label for the first line of text
        JLabel firstLine = new JLabel(operation.toString(), JLabel.CENTER);
        firstLine.setForeground(operation.getColor()); // Set the text color to white
        firstLine.setFont(new Font("Lucida Console", Font.BOLD, 18)); // Set the font for the first line

        // Create the second label for the second line of text
        JLabel secondLine = new JLabel("<html><div style='text-align: center; padding-left: 5px; padding-right: 5px;'>" + message + "</div></html>", JLabel.CENTER);
        secondLine.setForeground(Color.WHITE); // Set the text color to white
        secondLine.setFont(new Font("Lucida Console", Font.PLAIN, 16)); // Set the font for the second line

        // Create a panel for the two lines of text using BoxLayout
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Components.BUTTONCOLOR); // Set background color

        // Add the labels to the text panel with reduced spacing
        firstLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add a small rigid area to control spacing
        textPanel.add(firstLine);
        textPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Add a small rigid area to control spacing
        textPanel.add(secondLine);

        mainPanel.add(textPanel, BorderLayout.CENTER); // Add the text panel to the main panel

        // Create a wrapper panel to add a white border
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(Color.WHITE); // Set the background color to white
        wrapperPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5)); // Add a white border
        wrapperPanel.add(mainPanel, BorderLayout.CENTER); // Add the main panel to the wrapper panel

        // Add the wrapper panel to the window
        add(wrapperPanel); // Add the wrapper panel to the window
        setVisible(true);
    }



    
}
