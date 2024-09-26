/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class ConferenceTest {
    @Test
    public void testConferenceCreation() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000); // 1 día después
        List<String> topics = Arrays.asList("Tecnología", "Innovación");
        Conference conference = new Conference("Conferencia de Desarrollo", startDate, endDate, 100.0f, "Auditorio Principal", topics);
        
        assertEquals("Conferencia de Desarrollo", conference.getName());
        assertEquals(startDate, conference.getStartDate());
        assertEquals(endDate, conference.getEndDate());
        assertEquals(100.0f, conference.getRegistrationCost(), 0.01);
        assertEquals("Auditorio Principal", conference.getLocation());
        assertEquals(topics, conference.getTopics());
        assertTrue(conference.getId() > 0); // Verifica que el ID sea positivo y único
    }
    @Test
    public void testSetTopics() {
        Conference conference = new Conference("Conferencia de Desarrollo", new Date(), new Date(), 100.0f, "Auditorio Principal", Arrays.asList("Tecnología"));
        List<String> newTopics = Arrays.asList("Innovación", "Desarrollo");
        conference.setTopics(newTopics);
        assertEquals(newTopics, conference.getTopics());
    }
}
