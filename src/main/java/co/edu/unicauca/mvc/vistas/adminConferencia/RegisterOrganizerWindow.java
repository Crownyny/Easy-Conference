/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.mvc.vistas.adminConferencia;

import co.edu.unicauca.mvc.controllers.StorageService;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import co.edu.unicauca.mvc.models.Organizer;
import co.edu.unicauca.mvc.utilities.Elements;
import co.edu.unicauca.mvc.utilities.FieldConfig;
import co.edu.unicauca.mvc.utilities.Utilities;
import java.util.LinkedHashMap;

/**
 *
 * @author Default
 */
public class RegisterOrganizerWindow extends RegisterWindow {

    private final StorageService<Organizer> objStorageService;

    /**
     * Creates new form VtnListarArticulos
     * @param objStorageService
     */
    public RegisterOrganizerWindow (StorageService<Organizer> objStorageService) {
        super(new JLabel("Registrar Organizador"), createInputFields());
        this.objStorageService = objStorageService;
    }
    
    private static LinkedHashMap<String, FieldConfig> createInputFields() {
        LinkedHashMap<String, FieldConfig> inputFields = new LinkedHashMap<>();
        inputFields.put("Nombres:", new FieldConfig(new JTextField(20)));
        inputFields.put("Apellidos:", new FieldConfig(new JTextField(20)));
        inputFields.put("Mail:", new FieldConfig(new JTextField(20)));
        inputFields.put("Universidad:", new FieldConfig(new JTextField(20)));
        return inputFields;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void registerAction() {
        ArrayList<String> values = Elements.extractTextFields(fieldConfigs);

        Organizer organizer = new Organizer(values.get(0), values.get(1), values.get(2), values.get(3));

        if (objStorageService.store(organizer)) 
            Utilities.successMessage("El registro del organizador fue exitoso", "Registro exitoso");
        else
            Utilities.errorMessage("El registro del organizador no se realizo", "Error en el registro");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
