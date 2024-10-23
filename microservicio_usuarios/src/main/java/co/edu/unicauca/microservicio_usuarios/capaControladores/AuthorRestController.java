package co.edu.unicauca.microservicio_usuarios.capaControladores;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.AuthorDTO;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.services.IAuthorService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping("/authors")
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> listAuthors = authorService.findAll();
        return listAuthors;
    }

    @GetMapping("/authors/{id}")
    public AuthorDTO getAuthorById(@PathVariable int id) {
        AuthorDTO author = authorService.findById(id);
        return author;
    }

    @PostMapping("/authors")
    public AuthorDTO saveAuthor(@RequestBody AuthorDTO author) {
        AuthorDTO authorStored = authorService.store(author);
        return authorStored;
    }

    @PutMapping("/authors/{id}")
    public AuthorDTO updateAuthor(@PathVariable int id, @RequestBody AuthorDTO author) {
        AuthorDTO authorUpdated = authorService.update(id, author);
        return authorUpdated;
    }

    @DeleteMapping("/authors/{id}")
    public boolean deleteAuthor(@PathVariable int id) {
        return authorService.delete(id);
    }
}
