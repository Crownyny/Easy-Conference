package co.edu.unicauca.mvc.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.util.LinkedHashMap;

public class Elements extends JFrame {
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
    
    public static JButton addButton(JButton myButton, int fontSize) {
        myButton.setBorderPainted(false);
        myButton.setBackground(new Color(0x2c4464)); // Return to transparent background
        myButton.setForeground(Color.WHITE);
        myButton.setFont(new Font("Lucida Console", Font.BOLD, fontSize)); // Font size doesn't matter
        myButton.setFocusPainted(false);
        myButton.setContentAreaFilled(false);
        myButton.setOpaque(true); // Make the button opaque from the start

        myButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                myButton.setBackground(new Color(52, 112, 224)); // Hover color with transparency
                myButton.repaint(); // Repaint the button
                myButton.getParent().repaint(); // Ensure the parent container is also repainted
                myButton.getParent().revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                myButton.setBackground(new Color(0x2c4464)); // Return to transparent background
                myButton.repaint(); // Repaint the button and the container
                myButton.getParent().repaint(); // Ensure the parent container is also repainted
                myButton.getParent().revalidate();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                myButton.setBackground(new Color(52, 112, 224)); // Adjust background if needed
                myButton.repaint();
                myButton.getParent().repaint(); // Repaint the button's container
                myButton.getParent().revalidate(); // Revalidate the container's layout
            }
        });

        return myButton;
    }
    
    public static void updateButtonBackground(JButton button, Color color)
    {
        button.setBackground(color);
        button.repaint();
        button.getParent().repaint();
        button.getParent().revalidate();
    }
    
    public static Dimension defineSize() 
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * .25);
        int height = (int) (screenSize.height * .4);
        return new Dimension(width, height);
    }
    
    public static ArrayList<String> extractTextFields(LinkedHashMap<String, FieldConfig> fieldConfigs) {
        ArrayList<String> values = new ArrayList<>();
        fieldConfigs.values().stream()
            .map(FieldConfig::getFieldType)
            .filter(JTextField.class::isInstance)
            .map(JTextField.class::cast)
            .map(JTextField::getText)
            .forEach(values::add);
        return values;
    }
    
}
