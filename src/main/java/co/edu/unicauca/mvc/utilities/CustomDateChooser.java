/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.utilities;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/*
 * Custom DateChooser
 * This class is a custom date chooser that allows to set a placeholder
 * It is based on the JDateChooser class from the JCalendar library
 * It has a placeholder, a placeholder color, a text color, a background color, and a border color
 * It also has a default font and a default border
 */
public class CustomDateChooser extends JDateChooser {

    private String placeholder; // Placeholder text
    private Color initialBorderColor; // Initial border color
    private Color activeBorderColor; // Active border color
    private Color placeholderColor; // Placeholder color
    private Color textColor; // Text color
    private Color backgroundColor; // Background color
    private Font defaultFont;   // Default font
    private MatteBorder initialBorder; // Initial border
    private JTextField dateTextField;   // Date text field

    /*
     * Constructor
     * @param placeholder The placeholder text
     * 
     * The constructor initializes the custom date chooser with the given placeholder
     */
    public CustomDateChooser(String placeholder) {
        super();
        this.placeholder = placeholder;
        this.initialBorderColor = new Color(0x505a74);
        this.activeBorderColor = new Color(52, 112, 224);
        this.placeholderColor = new Color(0x86949f);
        this.textColor = new Color(0x0f0f1e);
        this.backgroundColor = new Color(0xD7EAF9);
        this.dateTextField = (JTextField) this.getDateEditor().getUiComponent();
        this.defaultFont = new Font("Leelawadee UI", Font.PLAIN, dateTextField.getFont().getSize());
        this.initialBorder = new MatteBorder(0, 0, 2, 0, initialBorderColor);

        // Configurar el JTextField interno
        setInitialState();
        addFocusBehavior();
        showPlaceHolder();
    }

    /*
     * Method to show the placeholder text
     */
    private void showPlaceHolder() {
        SwingUtilities.invokeLater(() -> { 
            if (dateTextField.getText().isEmpty()) {
                dateTextField.setText(placeholder);
                dateTextField.setForeground(placeholderColor);
            }
        });
    }

    /*
     * Method to add focus behavior (placeholder logical behavior)
     */
    private void addFocusBehavior() {
        dateTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateTextField.getText().equals(placeholder)) {
                    dateTextField.setText("");
                    dateTextField.setForeground(textColor);
                    dateTextField.setHorizontalAlignment(JLabel.LEFT);
                }
                dateTextField.setBorder(BorderFactory.createCompoundBorder(
                        new MatteBorder(0, 0, 2, 0, activeBorderColor),
                        BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dateTextField.getText().isEmpty()) {
                    dateTextField.setText(placeholder);
                    dateTextField.setForeground(placeholderColor);
                    dateTextField.setHorizontalAlignment(JLabel.CENTER);
                }
                dateTextField.setBorder(BorderFactory.createCompoundBorder(
                        initialBorder,
                        BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));
            }
        });
    }

    /*
     * Method to set the initial state of the custom date chooser
     */
    public void setInitialState() {
        dateTextField.setBackground(backgroundColor);
        dateTextField.setText(placeholder);
        dateTextField.setForeground(placeholderColor);
        dateTextField.setHorizontalAlignment(JLabel.CENTER);
        dateTextField.setFont(defaultFont);
        dateTextField.setBorder(BorderFactory.createCompoundBorder(
                initialBorder,
                BorderFactory.createEmptyBorder(0, 2, 0, 2)
        ));
        showPlaceHolder();
        setDate(null); // Set the date to null
    }
}

