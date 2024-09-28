/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.utilities;

import static co.edu.unicauca.mvc.utilities.Elements.errorColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class CustomTextField extends JTextField {
    private String placeholder;
    private Color initialBorderColor;
    private Color activeBorderColor;
    private Color placeholderColor;
    private Color textColor;
    private Font defaultFont;
    private MatteBorder initialBorder;

    public CustomTextField(String placeholder) {
        super(20);
        this.placeholder = placeholder;
        this.initialBorderColor = new Color(0x505a74);
        this.activeBorderColor = new Color(52, 112, 224);
        this.placeholderColor = new Color(0x86949f);
        this.textColor = new Color(0x0f0f1e);
        this.defaultFont = new Font("Leelawadee UI", Font.PLAIN, getFont().getSize());
        this.initialBorder = new MatteBorder(0, 0, 2, 0, initialBorderColor);

        // Configuración inicial
        setText(placeholder);
        setForeground(placeholderColor);
        setFont(defaultFont);
        setBackground(new Color(0xD7EAF9));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(BorderFactory.createCompoundBorder(
                initialBorder, 
                BorderFactory.createEmptyBorder(0, 2, 0, 2)
        ));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)|| getForeground().equals(errorColor)) {
                    setText("");
                    setForeground(textColor);
                    setHorizontalAlignment(JLabel.LEFT);
                }
                setBorder(BorderFactory.createCompoundBorder(
                        new MatteBorder(0, 0, 2, 0, activeBorderColor),
                        BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(placeholderColor);
                    setHorizontalAlignment(JLabel.CENTER);
                }
                setBorder(BorderFactory.createCompoundBorder(
                        initialBorder, 
                        BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));
            }
        });
    }

    public void resetToInitialState() {
        setText(placeholder);
        setForeground(placeholderColor);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(defaultFont);
        setBorder(BorderFactory.createCompoundBorder(
                initialBorder, 
                BorderFactory.createEmptyBorder(0, 2, 0, 2)
        ));
    }
}
