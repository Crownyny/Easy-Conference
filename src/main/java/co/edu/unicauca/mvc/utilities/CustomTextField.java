/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import static co.edu.unicauca.mvc.utilities.Components.*;

/**
 * Custom TextField
 * This class is a custom text field that allows to set a placeholder
 * It has a placeholder, a placeholder color, a text color, a background color, and a border color
 * It also has a default font and a default border
 */

 public class CustomTextField extends JTextField {
    private final String placeholder;
    private final Color PHOLDERCOLOR = new Color(0x86949f);
    private final Font defaultFont;
    private final MatteBorder initialBorder = new MatteBorder(0, 0, 2, 0, BUTTONCOLOR);

    /**
     * Constructor
     * @param placeholder The placeholder text
     * 
     * The constructor initializes the custom text field with the given placeholder
     */
    public CustomTextField(String placeholder) {
        super(20);
        this.placeholder = placeholder;
        Dimension dimensions = Components.getRelativeSize();
        defaultFont = new Font("Leelawadee UI", Font.PLAIN, Math.min(dimensions.width, dimensions.height) / 51);

        // Configuración inicial
        setText(placeholder);
        setForeground(PHOLDERCOLOR);
        setFont(defaultFont);
        setBackground(new Color(0xD7EAF9));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(createCompoundBorder(initialBorder, 5)); // Add padding

        addFocusListener(new FocusAdapter() {
            // Eventos de foco
            @Override
            public void focusGained(FocusEvent e) {
                /*
                * Si el texto es igual al placeholder o el color del texto es el de error
                * se borra el texto y se cambia el color del texto al color por defecto
                */
                if (isPlaceholder() ) {
                    setText("");
                    setForeground(TEXTCOLOR);
                }
                else if (getForeground().equals(ERRCOLOR)) {
                    setForeground(TEXTCOLOR);
                }

                /*
                 * Se cambia el borde inferior al color activo
                 */
                setBorder(createCompoundBorder(new MatteBorder(0, 0, 2, 0, ACTIVECOLOR), 5)); // Add padding
            }

            // Evento de pérdida de foco
            @Override
            public void focusLost(FocusEvent e) {
                /*
                 * Si el texto está vacío se vuelve a poner el placeholder
                 * y se cambia el color del texto al color del placeholder
                 */
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(PHOLDERCOLOR);
                    setFont(getFont().deriveFont(Font.PLAIN));
                }
                /*
                 * Se cambia el borde inferior al color inicial
                 */
                setBorder(createCompoundBorder(initialBorder, 5)); // Add padding
            }
        });
    }

    /**
     * Helper method to create a compound border with padding
     * @param border The main border
     * @param padding The padding to add
     * @return The compound border with padding
     */
    protected Border createCompoundBorder(Border border, int padding) {
        return BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(0, 0, padding, 0)
        );
    }

    /**
     * Method to set the custom text field back to its initial state
     */
    public void resetToInitialState() {
        setText(placeholder);
        setForeground(PHOLDERCOLOR);
        setFont(defaultFont);
        setBorder(BorderFactory.createCompoundBorder(
                initialBorder, 
                BorderFactory.createEmptyBorder(0, 2, 0, 2)
        ));
    }

    /*
     * isPlaceholder
     * @return True if the text is empty (equal to the placeholder)
     * False otherwise
     */
    public boolean isPlaceholder() {
        return getText().equals(placeholder);
    }


}