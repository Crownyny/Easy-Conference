package co.edu.unicauca.mvc.utilities;

import javax.swing.JComponent;

/**
 *
 * @author Default
 */
public class FieldConfig {
    private final JComponent fieldType;
    private final boolean optional;

    public FieldConfig(JComponent fieldType, boolean optional) {
        this.fieldType = fieldType;
        this.optional = optional;
    }
    
    public FieldConfig(JComponent fieldType) {
        this.fieldType = fieldType;
        this.optional = true;
    }


    public JComponent getFieldType() {
        return fieldType;
    }
    
    public boolean isOptional(){
        return optional;
    }
}