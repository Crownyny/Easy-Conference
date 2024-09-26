/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mvc.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class ArticleTest {
    @Test
    public void setArticleTest(){
        System.out.println("Test set Article");
        String expectedTitle = "Programacion", expectedJournal = "Semana";
        Article ar = new Article("Programacion", "Semana");
        assertEquals(expectedTitle, ar.getTitle());
        assertEquals(expectedJournal, ar.getJournal());
         assertTrue(ar.getId() > 0);
    }
    @Test
    public void testIdIncrement() {
        System.out.println("Test id increment");
        Article article1 = new Article("Programacion", "Semana");
        Article article2 = new Article("Java", "Revista");
        assertEquals(2, article1.getId()); // El primer artículo debería tener ID 2
        assertEquals(3, article2.getId()); // El segundo artículo debería tener ID 3
    }
}
