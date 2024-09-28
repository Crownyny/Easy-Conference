package co.edu.unicauca.mvc.utilities;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.Box;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class Elements extends JFrame {
    public static final Color errorColor = new Color(0xE81010);
    
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
    
    public static JButton addButton(String buttonText, JPanel container) {
        JButton myButton = new JButton(buttonText);
        container.add(myButton);
        container.add(Box.createHorizontalStrut(20)); 
        myButton.setBorderPainted(false);
        myButton.setBackground(new Color(0x2c4464)); // Return to transparent background
        myButton.setForeground(Color.WHITE);
        myButton.setFont(new Font("Lucida Console", Font.BOLD, 1)); // Fontsize doesn't matter
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
                myButton.setBackground(new Color(52, 112, 224)); // Adjust background if necessary
                myButton.repaint();
                myButton.getParent().repaint(); // Repaint the button's container
                myButton.getParent().revalidate(); // Revalidate the layout of the container
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
    
    public static Dimension getRelativeSize(double widthRatio, double heightRatio) 
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * widthRatio);
        int height = (int) (screenSize.height * heightRatio);
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
    
    public static JLabel createLabel(String text, int fontsize)
    {
        JLabel label = new JLabel(text);
        
        Color activeColor = new Color(52, 112, 224);
        Color initalColor = new Color(0x2c4464);        
        
        label.setForeground(initalColor);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Leelawadee UI",Font.PLAIN, fontsize));
        
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent  e) {
                label.setForeground(activeColor);
                label.setFont(new Font("Leelawadee UI", Font.BOLD,(int) (fontsize * 1.15)));
            }

            @Override
            public void mouseExited(MouseEvent  e) {
                label.setForeground(initalColor);
                label.setFont(new Font("Leelawadee UI", Font.PLAIN, fontsize));
            }
        });        
        return label;
    }
    
   public static JTextField createNumberField(int maxLength, String placeholder) {
        JTextField numberField = new CustomTextField(placeholder);
        //numberField.setColumns(maxLength);

        numberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                String text = numberField.getText();

                if (ch == KeyEvent.VK_BACK_SPACE) {
                    return;
                }

                if ((text.isEmpty() && ch == '0') || !Character.isDigit(ch)
                        || (text.length() >= maxLength)) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (!numberField.getText().isEmpty()) {
                        int value = Integer.parseInt(numberField.getText());
                        if (value < 0) {
                            numberField.setText("0");
                        }
                    }
                } catch (NumberFormatException ex) {
                    numberField.setText("0");
                }
            }
        });
        return numberField;
    }
    
    public static void centerJIF(JInternalFrame jif, JDesktopPane mainPanel) {
        Dimension desktopSize = mainPanel.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
}
