package co.edu.unicauca.mvc.vistas.mainWindowPanels;

import co.edu.unicauca.mvc.dataAccess.GeneralRepository;
import co.edu.unicauca.mvc.models.User;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.vistas.util.CardPanelManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/*
*
*@author DavidCachón
*/
public class SignInPanel extends JPanel
{
    private final CardPanelManager cardManager;
    private final String[] placeholders = {"  Nombres", "  Apellidos", "  Email", "  Contraseña" };

    public SignInPanel(CardPanelManager cardManager) {
        this.cardManager = cardManager;
        createPanel();
    }


    private void createPanel() {
          setBackground(new Color(0xD7EAF9));
          setLayout(new BorderLayout());

          // Create layered pane
          JLayeredPane layeredPane = new JLayeredPane();
          layeredPane.setLayout(null); // Disable automatic layout

          // Add the main center panel
          JPanel centerPanel = createCenterPanel();
          layeredPane.add(centerPanel, JLayeredPane.DEFAULT_LAYER);

          // Add the arrow in the top-right corner
          JLabel iconLabel = createIconLabel();
          layeredPane.add(iconLabel, JLayeredPane.PALETTE_LAYER);

          // Add a listener to reposition and resize components when the panel is resized
          addComponentListener(new ComponentAdapter() {
              @Override
              public void componentResized(ComponentEvent e) {
                  updateComponentBounds(layeredPane, centerPanel, iconLabel);
              }
          });

          add(layeredPane, BorderLayout.CENTER);
      }

      private JPanel createCenterPanel() {
          JPanel centerPanel = new JPanel(new GridBagLayout());
          centerPanel.setBackground(new Color(0xD7EAF9)); // Direct color value

          GridBagConstraints gbc = new GridBagConstraints();
          gbc.gridx = 0;
          gbc.gridy = 0;
          gbc.anchor = GridBagConstraints.CENTER;
          gbc.fill = GridBagConstraints.NONE;
          gbc.weightx = 1;
          gbc.weighty = 1;

          JPanel boxPanel = new JPanel(new GridBagLayout());
          boxPanel.setPreferredSize(Elements.getRelativeSize(0.25, 0.4));
          boxPanel.setBackground(new Color(0xD7EAF9)); // Direct color value
          addRowsToBoxPanel(boxPanel);
          centerPanel.add(boxPanel, gbc);
          return centerPanel;
      }

    private JLabel createIconLabel() {
        JLabel iconLabel = new JLabel();
        try {
            BufferedImage icon = loadAndScaleIcon("/resources/left_arrow.png");
            iconLabel.setIcon(new ImageIcon(icon));
            iconLabel.setPreferredSize(new Dimension(icon.getWidth(), icon.getHeight())); // Set preferred size

            // Add mouse listener for cursor and scaling effects
            iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    cardManager.showPanel("logInPanel");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    // Scale up the icon
                    iconLabel.setIcon(new ImageIcon(scaleIcon(icon, 1.2))); // Increase size by 20%
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    // Reset to original size
                    iconLabel.setIcon(new ImageIcon(scaleIcon(icon, 1.0))); // Reset to original size
                }
            });
        } catch (IOException e) {
        }
        return iconLabel;
    }

    private BufferedImage scaleIcon(BufferedImage icon, double scaleFactor) {
        int newWidth = (int) (icon.getWidth() * scaleFactor);
        int newHeight = (int) (icon.getHeight() * scaleFactor);
        Image scaledImage = icon.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage scaledIcon = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledIcon.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return scaledIcon;
    }


      private BufferedImage loadAndScaleIcon(String path) throws IOException {
          BufferedImage icon = ImageIO.read(getClass().getResource(path));
          Image iconScaled = icon.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Fixed size directly

          BufferedImage bufferedIcon = new BufferedImage(iconScaled.getWidth(null), iconScaled.getHeight(null), BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2d = bufferedIcon.createGraphics();
          g2d.drawImage(iconScaled, 0, 0, null);
          g2d.dispose();

          float[] scales = { 0.0f, 0.5f, 1f, 1.0f };
          RescaleOp op = new RescaleOp(scales, new float[4], null);
          return op.filter(bufferedIcon, null);
      }

      private void updateComponentBounds(JLayeredPane layeredPane, JPanel centerPanel, JLabel iconLabel) {
          layeredPane.setBounds(0, 0, getWidth(), getHeight());
          centerPanel.setBounds(0, 0, getWidth(), getHeight());
          iconLabel.setBounds(getWidth() - 40 - 20, 10, 40, 40); // Fixed size directly
      }

    
    private void addRowsToBoxPanel(JPanel boxPanel) 
    {
        List<JTextField> inputs = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // First row with the centered icon
        gbc.gridy = 0;
        gbc.weighty = 0.4;
        int logoFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 5;

        JPanel row1 = createRowPanel(new Color(0xD7EAF9));
        row1.setLayout(new GridBagLayout());

        try {
            BufferedImage iconRegister = ImageIO.read(getClass().getResource("/resources/login_icon.png"));
            Image iconScaled = iconRegister.getScaledInstance(logoFontSize, logoFontSize, Image.SCALE_SMOOTH);

            // Apply a color scale filter to darken the icon
            BufferedImage bufferedIcon = new BufferedImage(iconScaled.getWidth(null), iconScaled.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedIcon.createGraphics();
            g2d.drawImage(iconScaled, 0, 0, null);
            g2d.dispose();

            float[] scales = { 0.5f, 0.5f, 0.7f, 1.0f };
            RescaleOp op = new RescaleOp(scales, new float[4], null);
            bufferedIcon = op.filter(bufferedIcon, null);

            JLabel iconLabel = new JLabel(new ImageIcon(bufferedIcon));

            GridBagConstraints iconConstraints = new GridBagConstraints();
            iconConstraints.gridx = 0;
            iconConstraints.gridy = 0;
            iconConstraints.anchor = GridBagConstraints.CENTER;
            row1.add(iconLabel, iconConstraints);
        } catch (IOException e) {}

        boxPanel.add(row1, gbc);

        // Rows with text fields
        gbc.weighty = 0.45;


        for (int i = 1; i <= 4; i++) {
            gbc.gridy = i;
            JPanel row = createRowPanel(new Color(0xD7EAF9));

            JTextField inputField = createInputField(placeholders[i - 1]);
            inputField.setPreferredSize(new Dimension(1, (int) (boxPanel.getPreferredSize().height * 0.1)));

            row.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;

            inputs.add(inputField);
            row.add(inputField, constraints);
            boxPanel.add(row, gbc);
        }

        // "Ingresar" button
        gbc.gridy = 5;
        gbc.weighty = 0.3;
        int buttonFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 25;
        JButton mainButton = Elements.addButton(new JButton("Registrarse"), buttonFontSize);
        mainButton.addActionListener(e -> signInAction(inputs));
        boxPanel.add(mainButton, gbc);
    }
    

    
    private JPanel createRowPanel(Color color) 
    {
        JPanel panel = new JPanel();
        panel.setBackground(color); 
        return panel;
    }
    
    private JTextField createInputField(String placeholder) 
    {
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
        input.setFont(new Font("Leelawadee UI",Font.PLAIN, fontsize));

        input.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (input.getText().equals(placeholder) || input.getForeground().equals(Elements.errorColor) ) {
                    input.setText("");
                    input.setForeground(textColor);  // Set to normal text color
                    input.setHorizontalAlignment(JLabel.LEFT);
                }
                // Change the border color when the text field is focused
                input.setBorder(BorderFactory.createCompoundBorder(
                    new MatteBorder(0, 0, 2, 0, activeBorderColor),
                    BorderFactory.createEmptyBorder(0, 2, 0, 2)
                ));
                
                input.setFont(new Font("Leelawadee UI",Font.PLAIN, fontsize));
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
    
    private void signInAction(List<JTextField> inputs)
    {
        JTextField inputEmail = inputs.get(2);
        JTextField inputPassword = inputs.get(3);
       
        String password = inputPassword.getText();
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String upperCaseRegex = ".*[A-Z].*";
        String lowerCaseRegex = ".*[a-z].*";
        String digitRegex = ".*\\d.*";
        String specialCharRegex = ".*[@$!%*?&].*";
        String minLengthRegex = ".{8,}";
        String[] messages = {"Debes ingresar tu nombre", "Debes ingresar tu apellido"
                , "Debes ingresar un email valido", "Debes ingresar una contraseña valida"};        
        
        for (int i = 0; i < 2; i++)
        {
            if (inputs.get(i).getText().equals(placeholders[i]) || inputs.get(i).getText().isEmpty())
            {
                inputError(inputs.get(i), messages[i]);
                return;
            }
        }

        if (!inputEmail.getText().matches(emailRegex)) {
            inputError(inputEmail, messages[2]);
            return;
        }
        
        if (!password.matches(passwordRegex)) {
            if (!password.matches(minLengthRegex)) {
            inputError(inputPassword, "Password must be at least 8 characters long.");
            } else if (!password.matches(upperCaseRegex)) {
                inputError(inputPassword, "Password must contain at least one uppercase letter.");
            } else if (!password.matches(lowerCaseRegex)) {
                inputError(inputPassword, "Password must contain at least one lowercase letter.");
            } else if (!password.matches(digitRegex)) {
                inputError(inputPassword, "Password must contain at least one digit.");
            } else if (!password.matches(specialCharRegex)) {
                inputError(inputPassword, "Password must contain at least one special character.");
} 
            return;
        }
        
        createAccount(inputs);
        cardManager.showPanel("logInPanel");
    }
     
    private void inputError(JTextField input, String message)
    {
        MatteBorder errorBorder = new MatteBorder(0, 0, 2, 0, Elements.errorColor);
        int fontsize = input.getFont().getSize();
        input.setBorder(BorderFactory.createCompoundBorder(
            errorBorder, 
            BorderFactory.createEmptyBorder(0, 2, 0, 2) 
        ));

        input.setHorizontalAlignment(JLabel.CENTER);
        input.setText(message);
        input.setForeground(Elements.errorColor);
        input.setFont(new Font("Leelawadee UI",Font.BOLD, fontsize));
    }
    
    private void createAccount(List<JTextField> inputs)
    {
        User newUser = new User(inputs.get(2).getText(), inputs.get(3).getText(), 
                inputs.get(0).getText(), inputs.get(1).getText());
        GeneralRepository.storeUser(newUser);
    }
}
