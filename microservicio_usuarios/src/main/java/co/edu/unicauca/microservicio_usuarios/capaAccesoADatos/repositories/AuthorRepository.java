package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.Author;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {
    private ArrayList<Author> listAuthors;

    public AuthorRepository() {
        this.listAuthors = new ArrayList<>();
    }

    public ArrayList<Author> getAllAuthors() {
        return listAuthors;
    }

    public Author getAuthorById(int id) {
        Author author = null;
        for (Author a : listAuthors) {
            if (a.getId() == id) {
                author = a;
                break;
            }
        }
        return author;
    }

    public Author store(Author author) {
        Author autor = null;
        if(listAuthors.add(author)) {
            autor = author;
        }
        return autor;
    }

    /*public void store(Author ... authors) {
        for(Author author : authors){
            listAuthors.add(author);
        }
    }*/

    public Author update(Author author) {
        Author autor = null;
        for (Author a : listAuthors) {
            if (a.getId() == author.getId()) {
                a.setFirstName(author.getFirstName());
                a.setLastName(author.getLastName());
                a.setMail(author.getMail());
                a.setTypeAuthor(author.getTypeAuthor());
                autor = a;
                break;
            }
        }
        return autor;
    }

    public boolean delete(int id) {
        boolean result = false;
        for (Author a : listAuthors) {
            if (a.getId() == id) {
                listAuthors.remove(a);
                result = true;
                break;
            }
        }
        return result;
    }
}
