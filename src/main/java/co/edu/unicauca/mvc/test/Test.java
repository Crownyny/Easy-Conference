package co.edu.unicauca.mvc.test;


import co.edu.unicauca.mvc.accesoADatos.RepositorioArticuloMemoriaArrayList;
import co.edu.unicauca.mvc.accesoADatos.RepositorioConferenciaMemoriaArrayList;
import co.edu.unicauca.mvc.accesoADatos.RepositorioOrganizadorMemoriaArrayList;
import co.edu.unicauca.mvc.controladores.*;
import co.edu.unicauca.mvc.vistas.adminConferencia.VtnPrincipalAdmin;
import co.edu.unicauca.mvc.vistas.asistente.VtnPrincipalAsistente;
import co.edu.unicauca.mvc.vistas.autorPublicacion.VtnPrincipalAutor;
import javax.swing.UIManager;



public class Test {

    
    public static void main(String[] args) {
        
        seleccionarLookAndField();
                
        RepositorioConferenciaMemoriaArrayList objRepositorio
                = new RepositorioConferenciaMemoriaArrayList();
        ServicioAlmacenamientoConferencias objServicio
                = new   ServicioAlmacenamientoConferencias(objRepositorio);  
        
        RepositorioOrganizadorMemoriaArrayList objRepositorio2
                = new RepositorioOrganizadorMemoriaArrayList();
        ServicioAlmacenamientoOrganizadores objServicio2
                = new ServicioAlmacenamientoOrganizadores(objRepositorio2);   
        
        RepositorioArticuloMemoriaArrayList objRepositorio3
                = new RepositorioArticuloMemoriaArrayList();
        ServicioAlmacenamientoArticulos objServicio3
                = new ServicioAlmacenamientoArticulos(objRepositorio3);   
         
        VtnPrincipalAsistente objVtnAsistente=new VtnPrincipalAsistente();
        VtnPrincipalAutor objVtnAutor= new VtnPrincipalAutor();
        
        objServicio.addObserver(objVtnAsistente);
        objServicio.addObserver(objVtnAutor);
        objServicio2.addObserver(objVtnAsistente);
        objServicio2.addObserver(objVtnAutor);
        objServicio3.addObserver(objVtnAsistente);
        objServicio3.addObserver(objVtnAutor);
        
        VtnPrincipalAdmin objVtnPrincipal= new VtnPrincipalAdmin();  
        objVtnPrincipal.asociarServicio(ServicioAlmacenamientoConferencias.class, objServicio);
        objVtnPrincipal.asociarServicio(ServicioAlmacenamientoOrganizadores.class, objServicio2);
        objVtnPrincipal.asociarServicio(ServicioAlmacenamientoArticulos.class, objServicio3); 
        objVtnPrincipal.setVisible(true);
        objVtnAsistente.setVisible(true);
        objVtnAutor.setVisible(true);
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
