/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class AuthorTest {
    @Test
    public void setAuthorTest(){
        String expectedFirstName = "Kylian", expectedLastName = "Mbappe", expectedMail = "kylian@gmail.com", expectedTypeAuthor = "Independiente";
        Author a = new Author("Kylian", "Mbappe", "kylian@gmail.com", "Independiente");
        assertEquals(expectedFirstName, a.getFirstName());
        assertEquals(expectedLastName, a.getLastName());
        assertEquals(expectedMail, a.getMail());
        assertEquals(expectedTypeAuthor, a.getTypeAuthor());
    }
}
