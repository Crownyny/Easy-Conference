package co.edu.unicauca.mvc.test;

import co.edu.unicauca.mvc.controllers.ConferenceManagementService;
import co.edu.unicauca.mvc.controllers.StorageService;
import co.edu.unicauca.mvc.dataAccess.MemoryArrayListRepository;
import co.edu.unicauca.mvc.vistas.adminConferencia.MainWindow;
import co.edu.unicauca.mvc.vistas.asistente.VtnPrincipalAsistente;
import co.edu.unicauca.mvc.vistas.autorPublicacion.VtnPrincipalAutor;
import co.edu.unicauca.mvc.vistas.panels.MainPanel;
import java.util.HashMap;
import javax.swing.UIManager;

public class Test {

    
    public static void main(String[] args) {
        seleccionarLookAndField();
        // Add observers to all services
        VtnPrincipalAsistente assistantWindow = new VtnPrincipalAsistente();
       VtnPrincipalAutor authorWindow = new VtnPrincipalAutor();
           
        HashMap<Class<?>, StorageService<?>> serviceMap = new HashMap<>();

        MemoryArrayListRepository<ConferenceManagementService> conferenceRepository = new MemoryArrayListRepository<>();
        StorageService<ConferenceManagementService> conferenceService = new StorageService<>(conferenceRepository);
        serviceMap.put(ConferenceManagementService.class, conferenceService);

        // Associate all services with the 6admin window
        MainWindow adminWindow = new MainWindow();
        for (Class<?> entityType : serviceMap.keySet()) {
            MainPanel mainPanel = (MainPanel) adminWindow.getCardManager().getPanel("mainPanel");
            mainPanel.associateService(entityType, serviceMap.get(entityType));
        }

        // Show windows
        adminWindow.setVisible(true);
        assistantWindow.setVisible(true);
        authorWindow.setVisible(true);
    }
    
    private static void seleccionarLookAndField()
    {
        for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
                 } catch (Exception ex) {
            }
        }
    }
    
    
    
    
    
    
}
