package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.User;
import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories.UserRepository;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.UserDTO;

@Service
public class UserServiceImp implements IUserService {
    private UserRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public UserServiceImp(UserRepository servicioAccesoBaseDatos, ModelMapper mapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = mapper;
    }

    @Override
    public List<UserDTO> findAll() {
        System.out.println("Retornando todos los usuarios");
        List<User> listUsers = servicioAccesoBaseDatos.getAllUsers();
        List<UserDTO> listUsersDTO = this.modelMapper.map(listUsers, new TypeToken<List<UserDTO>>() {
        }.getType());
        return listUsersDTO;
    }

    @Override
    public UserDTO findById(int id) {
        System.out.println("Buscando usuario por id");
        User user = servicioAccesoBaseDatos.getUserById(id);
        UserDTO userDTO = null;
        if(user != null){
            userDTO = this.modelMapper.map(user, UserDTO.class);
        }
        return userDTO;
    }

    @Override
    public UserDTO store(UserDTO user) {
        System.out.println("Guardando usuario");
        User userEntity = this.modelMapper.map(user, User.class);
        User userStored = servicioAccesoBaseDatos.store(userEntity);
        UserDTO userDTO = this.modelMapper.map(userStored, UserDTO.class);
        return userDTO;
    }

    // @Override
    // public void store(UserDTO ... users) {
    // System.out.println("Guardando lista de usuarios");
    //  for(UserDTO user : users){
    //  User userEntity = this.modelMapper.map(user, User.class);
    //   servicioAccesoBaseDatos.store(userEntity);
    //  }
    // }

    @Override
    public UserDTO update(int id, UserDTO user) {
        System.out.println("Actualizando usuario");
        User userEntity = this.modelMapper.map(user, User.class);
        userEntity.setId(id);
        User userUpdated = servicioAccesoBaseDatos.update(userEntity);
        UserDTO userDTO = this.modelMapper.map(userUpdated, UserDTO.class);
        return userDTO;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("Eliminando usuario");
        return servicioAccesoBaseDatos.delete(id);
    }
}
