/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 *
 * @author david
 */
public class Elements {
    public static final Color errorColor = new Color(0xE81010);
    public static JTextField createInputField(String placeholder) {
        JTextField input = new JTextField(20);

        Color initialBorderColor = new Color(0x505a74);
        Color activeBorderColor = new Color(52, 112, 224);
        Color placeholderColor = new Color(0x86949f);
        Color textColor = new Color(0x0f0f1e);

        int fontsize = input.getFont().getSize();
        MatteBorder initialBorder = new MatteBorder(0, 0, 2, 0, initialBorderColor);
        input.setBorder(BorderFactory.createCompoundBorder(
                initialBorder,
                BorderFactory.createEmptyBorder(0, 2, 0, 2) // Padding de 10px en los lados izquierdo y derecho
        ));

        input.setBackground(new Color(0xD7EAF9));

        input.setText(placeholder);
        input.setHorizontalAlignment(JLabel.CENTER);
        input.setForeground(placeholderColor);
        input.setFont(new Font("Leelawadee UI", Font.PLAIN, fontsize));

        input.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (input.getText().equals(placeholder) || input.getForeground().equals(errorColor)) {
                    input.setText("");
                    input.setForeground(textColor);  // Set to normal text color
                    input.setHorizontalAlignment(JLabel.LEFT);
                }
                // Change the border color when the text field is focused
                input.setBorder(BorderFactory.createCompoundBorder(
                        new MatteBorder(0, 0, 2, 0, activeBorderColor),
                        BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));

                input.setFont(new Font("Leelawadee UI", Font.PLAIN, fontsize));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (input.getText().isEmpty()) {
                    input.setText(placeholder);
                    input.setForeground(placeholderColor);  // Set to placeholder color
                    input.setHorizontalAlignment(JLabel.CENTER);
                }
                // Revert to the initial border color when focus is lost
                input.setBorder(BorderFactory.createCompoundBorder(
                        new MatteBorder(0, 0, 2, 0, initialBorderColor),
                        BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));
            }
        });

        return input;
    }
}
