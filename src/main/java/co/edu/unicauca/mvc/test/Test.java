package co.edu.unicauca.mvc.test;

import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import co.edu.unicauca.mvc.models.User;
import co.edu.unicauca.mvc.vistas.panels.LoginPanel;
import javax.swing.JFrame;


public class Test {

    
    public static void main(String[] args) {
        // Add observers to services
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Tamaño máximo

        MemoryArrayListRepository<User> userRepository = new MemoryArrayListRepository<>();
        StorageService<User> userService = new StorageService<>(userRepository);
        userService.store(new User("Julian","Meneses","something@gmail.com","123"));
        LoginPanel loginPanel = new LoginPanel(userService);
        frame.add(loginPanel);
        frame.setVisible(true);
    }
    
    
    
}
