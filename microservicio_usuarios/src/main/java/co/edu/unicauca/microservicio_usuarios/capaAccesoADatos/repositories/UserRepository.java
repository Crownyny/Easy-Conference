package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.User;

@Repository
public class UserRepository {
    private ArrayList<User> listUsers;

    public UserRepository() {
        this.listUsers = new ArrayList<>();
    }

    public ArrayList<User> getAllUsers() {
        return listUsers;
    }

    public User getUserById(int id) {
        User user = null;
        for (User u : listUsers) {
            if (u.getId() == id) {
                user = u;
                break;
            }
        }
        return user;
    }

    public User store(User user) {
        User usuario = null;
        if(listUsers.add(user)) {
            usuario = user;
        }
        return usuario;
    }

    /*public void store(User ... users) {
        for(User user : users){
            listUsers.add(user);
        }
    }*/

    public User update(User user) {
        User usuario = null;
        for (User u : listUsers) {
            if (u.getId() == user.getId()) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setMail(user.getMail());
                u.setPassword(user.getPassword());
                usuario = u;
                break;
            }
        }
        return usuario;
    }

    public boolean delete(int id) {
        boolean result = false;
        for (User u : listUsers) {
            if (u.getId() == id) {
                listUsers.remove(u);
                result = true;
                break;
            }
        }
        return result;
    }
}
