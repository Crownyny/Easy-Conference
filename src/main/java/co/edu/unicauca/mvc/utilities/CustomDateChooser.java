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

public class CustomDateChooser extends JDateChooser {

    private String placeholder;
    private Color initialBorderColor;
    private Color activeBorderColor;
    private Color placeholderColor;
    private Color textColor;
    private Color backgroundColor;
    private Font defaultFont;
    private MatteBorder initialBorder;
    private JTextField dateTextField;

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
    private void showPlaceHolder() {
        SwingUtilities.invokeLater(() -> { //Se asegura de que se muestre el placeholder correctamente
            if (dateTextField.getText().isEmpty()) {
                dateTextField.setText(placeholder);
                dateTextField.setForeground(placeholderColor);
            }
        });
    }
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

    // Método para restablecer el estado original
    void setInitialState() {
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
        setDate(null); // Limpiar la fecha seleccionada
    }
}

