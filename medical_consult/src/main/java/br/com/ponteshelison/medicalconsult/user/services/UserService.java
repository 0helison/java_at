package br.com.ponteshelison.medicalconsult.user.services;

import br.com.ponteshelison.medicalconsult.user.domain.User;
import br.com.ponteshelison.medicalconsult.user.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return  userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User showUser(Long id) {
        return  userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User not found", id)
        );
    }

    public void deleteUser(Long id) {
        User user = showUser(id);
        userRepository.delete(user);
    }

    public User updateUser(Long id, User user) {
        User upUser = showUser(id);
        upUser.setUsername(user.getUsername());
        upUser.setEmail(user.getEmail());
        upUser.setCpf(upUser.getCpf());
        upUser.setPhone(user.getPhone());
        upUser.setBirthday(user.getBirthday());
        upUser.setPermission(user.getPermission());
        return  userRepository.save(upUser);
    }
}
