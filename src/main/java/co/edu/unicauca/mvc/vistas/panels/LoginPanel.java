package co.edu.unicauca.mvc.vistas.panels;

import co.edu.unicauca.mvc.controllers.ConferenceManagementService;
import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.controllers.UserManagementService;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.vistas.adminConferencia.MainWindow;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;


public class LogInPanel extends JPanel {
    private final StorageService<UserManagementService> users;
    private final MainWindow adminWindow;

    public LogInPanel( MainWindow adminWindow, StorageService<UserManagementService> users) {
        this.users = users;
        this.adminWindow = adminWindow;
        createPanel();
    }

    private void createPanel() {
        setBackground(new Color(0xD7EAF9));
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 1;
        gbc.weighty = 1; 

        JPanel boxPanel = new JPanel(new GridBagLayout());
        boxPanel.setPreferredSize(Elements.defineSize()); 
        boxPanel.setBackground(new Color(0xD7EAF9)); 
        addRowsToBoxPanel(boxPanel);

        add(boxPanel, gbc);
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
        String[] texts = { "  Email", "  Contraseña" };

        for (int i = 1; i <= 2; i++) {
            gbc.gridy = i;
            JPanel row = createRowPanel(new Color(0xD7EAF9));

            JTextField inputField = Elements.createInputField(texts[i - 1]);
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
        gbc.gridy = 3;
        gbc.weighty = 0.2;
        int buttonFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 21;
        JButton mainButton = Elements.addButton(new JButton("Ingresar"), buttonFontSize);
        mainButton.addActionListener(e -> loginAction(inputs));
        boxPanel.add(mainButton, gbc);

        // Final label
        gbc.weighty = 0.3;
        gbc.gridy = 4;
        int labelFontSize = Math.min(boxPanel.getPreferredSize().width, boxPanel.getPreferredSize().height) / 27;
        JLabel accountLabel = createLabel("No tienes una cuenta? Registrate!", labelFontSize);
        accountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                adminWindow.getCardManager().showPanel("signInPanel");
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                accountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                accountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        boxPanel.add(accountLabel, gbc);
    }

    private JPanel createRowPanel(Color color) 
    {
        JPanel panel = new JPanel();
        panel.setBackground(color); 
        return panel;
    }

    private JLabel createLabel(String text, int fontsize)
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

    private void loginAction(List<JTextField> inputs) 
    {
        String email = inputs.get(0).getText();
        String password = inputs.get(1).getText();
        JTextField inputEmail = inputs.get(0);
        JTextField inputPassword = inputs.get(1);
        MatteBorder errorBorder = new MatteBorder(0, 0, 2, 0, Elements.errorColor);
        int fontsize = inputEmail.getFont().getSize();

        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(emailRegex)) {
            inputEmail.setBorder(BorderFactory.createCompoundBorder(
                errorBorder, 
                BorderFactory.createEmptyBorder(0, 2, 0, 2) 
            ));
            inputEmail.setHorizontalAlignment(JLabel.CENTER);
            inputEmail.setText("Email invalido");
            inputEmail.setForeground(Elements.errorColor);
            inputEmail.setFont(new Font("Leelawadee UI",Font.BOLD, fontsize));
            return;
        }
        
        for (UserManagementService user : users.listAll())
        {
            String userPassword = user.getUser().getPassword();
            String userEmail = user.getUser().getMail();

            if (password.equals(userPassword) & userEmail.equals(email)) {
                setMainPanel();
                return;
            }            
        }
        
        inputPassword.setBorder(BorderFactory.createCompoundBorder(
                errorBorder, 
                BorderFactory.createEmptyBorder(0, 2, 0, 2) 
            ));
        inputEmail.setHorizontalAlignment(JLabel.CENTER);
        inputPassword.setText("La Contraseña y el Email no coinciden");
        inputPassword.setForeground(Elements.errorColor);
        inputPassword.setFont(new Font("Leelawadee UI",Font.BOLD, fontsize));
    }
    
    private void setMainPanel()
    {
        MemoryArrayListRepository<ConferenceManagementService> conferenceRepository = new MemoryArrayListRepository<>();
        StorageService<ConferenceManagementService> conferenceService = new StorageService<>(conferenceRepository);
        
        MainPanel mainPanel = (MainPanel) adminWindow.getCardManager().getPanel("mainPanel");
        mainPanel.associateService(ConferenceManagementService.class, conferenceService);
        adminWindow.getCardManager().showPanel("mainPanel");
    }

    
    public Dimension defineSize() 
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * .25);
        int height = (int) (screenSize.height * .4);
        return new Dimension(width, height);
    }

}
