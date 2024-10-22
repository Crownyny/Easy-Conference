package co.edu.unicauca.mvc.utilities;


import static co.edu.unicauca.mvc.utilities.Components.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.MatteBorder;

/*
 * CustomPwdField
 * This class is a custom password field that allows to set a placeholder
 * Also in contrast with the native password field, this class allows to get the text
 * and have a placeholder
 */
public class CustomPwdField extends CustomTextField{
    private final StringBuilder pwd;

    /**
     * Constructor
     * @param placeholder The placeholder text
     * 
     * The constructor initializes the custom password field with the given placeholder
     * It also initializes the pwd attribute
     * And adds the password behavior
     */
    public CustomPwdField(String placeholder) {
        super(placeholder);
        this.pwd = new StringBuilder();
        passwordBehavior();
    }

    private void passwordBehavior() {
        // KeyListener to capture the typed characters
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // If the character is not a control character
                // It is added to the pwd attribute
                if (!Character.isISOControl(c)) {
                    // When a character is typed, it is added to pwd
                    pwd.append(c);
                    // And the visible text is updated with an asterisk
                    setText(new String(new char[pwd.length()]).replace('\0', '*'));
                    e.consume();
                } 
            }

            @Override
            public void keyPressed(KeyEvent e) {
                /*
                 * If the character is a backspace
                 * And there are characters in pwd
                 * The last character is removed from pwd
                 */
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE || pwd.length() <= 0 ) {         
                    // Prevenimos la entrada de caracteres no válidos
                    e.consume();
                    return;
                } 

                // Get the selected text
                int start = getSelectionStart(); 
                int end = getSelectionEnd();

                // If there is selected text
                if (start != end) {
                    start+=1; // IDK why +1 is needed but it works
                    // Remove the selected text from pwd
                    pwd.delete(start, end);
                    // Update the visible text with asterisks
                    setText(new String(new char[pwd.length()]).replace('\0', '*'));
                    setCaretPosition(start); // Move caret to the start of the removed text
                    e.consume();
                }

                // When backspace is pressed, remove the last character if no text is selected
                pwd.deleteCharAt(pwd.length() - 1);
                // And the visible text is removing the last character
                setText(new String(new char[pwd.length()]).replace('\0', '*'));
                e.consume();
            }
        });
    
        addFocusListener(new FocusAdapter() {
            // Eventos de foco
            @Override
            public void focusGained(FocusEvent e) {
                /*
                * Si el texto es igual al placeholder o el color del texto es el de error
                * se borra el texto y se cambia el color del texto al color por defecto
                */
                if (pwd.isEmpty() || getForeground().equals(ERRCOLOR)) {
                    setText("");
                    setForeground(TEXTCOLOR);
                }
                /*
                 * Se cambia el borde inferior al color activo
                 */
                setBorder(createCompoundBorder(new MatteBorder(0, 0, 2, 0, ACTIVECOLOR), 5)); // Add padding
            }
        });
    }
    @Override
    public String getText() {
        return pwd.toString();
    }
}
