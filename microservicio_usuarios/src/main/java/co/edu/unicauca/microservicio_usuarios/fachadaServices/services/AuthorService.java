package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.Author;
import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories.AuthorRepository;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.AuthorDTO;

@Service
public class AuthorService implements IAuthorService {
    private AuthorRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public AuthorService(AuthorRepository servicioAccesoBaseDatos, ModelMapper mapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = mapper;
    }

    @Override
    public List<AuthorDTO> findAll() {
        System.out.println("Retornando todos los autores");
        List<Author> listAuthors = servicioAccesoBaseDatos.getAllAuthors();
        List<AuthorDTO> listAuthorsDTO = this.modelMapper.map(listAuthors, new TypeToken<List<AuthorDTO>>() {
        }.getType());
        return listAuthorsDTO;
    }

    @Override
    public AuthorDTO findById(int id) {
        System.out.println("Buscando autor por id");
        Author author = servicioAccesoBaseDatos.getAuthorById(id);
        AuthorDTO authorDTO = null;
        if(author != null){
            authorDTO = this.modelMapper.map(author, AuthorDTO.class);
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO store(AuthorDTO author) {
        System.out.println("Guardando autor");
        Author authorEntity = this.modelMapper.map(author, Author.class);
        Author authorStored = servicioAccesoBaseDatos.store(authorEntity);
        AuthorDTO authorDTO = this.modelMapper.map(authorStored, AuthorDTO.class);
        return authorDTO;
    }

    // @Override
    // public void store(AuthorDTO ... authors) {
    // System.out.println("Guardando lista de autores");
    //  for(AuthorDTO author : authors){
    //  Author authorEntity = this.modelMapper.map(author, Author.class);
    //   servicioAccesoBaseDatos.store(authorEntity);
    //  }
    // }

    @Override
    public AuthorDTO update(int id, AuthorDTO author) {
        System.out.println("Actualizando autor");
        Author authorEntity = this.modelMapper.map(author, Author.class);
        authorEntity.setId(id);
        Author authorUpdated = servicioAccesoBaseDatos.update(authorEntity);
        AuthorDTO authorDTO = this.modelMapper.map(authorUpdated, AuthorDTO.class);
        return authorDTO;
    }

    @Override
    public boolean delete(int id) {
        return servicioAccesoBaseDatos.delete(id);
    }
}
