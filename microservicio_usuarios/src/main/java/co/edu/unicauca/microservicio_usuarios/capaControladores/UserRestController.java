package co.edu.unicauca.microservicio_usuarios.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.services.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.UserDTO;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRestController {

    // Inyectar el servicio de usuarios
    @Autowired
    private IUserService userService;

    // Este servicio REST lista todos los usuarios
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> listUsers = userService.findAll();
        return listUsers;
    }

    // Este servicio REST busca un usuario por su id
    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        UserDTO user = userService.findById(id);
        return user;
    }

    // Este servicio REST guarda un usuario
    @PostMapping("/users")
    public UserDTO saveUser(@RequestBody UserDTO user) {
        UserDTO userStored = userService.store(user);
        return userStored;
    }

    // Este servicio REST guarda una lista de usuarios
    // @PostMapping("/users2")
    // public void saveUsers(@RequestBody UserDTO ... users) {
    // userService.store(users);
    // }

    // Este servicio REST actualiza un usuario
    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        UserDTO userUpdated = userService.update(id, user);
        return userUpdated;
    }

    // Este servicio REST elimina un usuario
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }
    
}
