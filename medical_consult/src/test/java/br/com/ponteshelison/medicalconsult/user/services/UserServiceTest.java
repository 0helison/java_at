package br.com.ponteshelison.medicalconsult.user.services;

import br.com.ponteshelison.medicalconsult.user.domain.User;
import br.com.ponteshelison.medicalconsult.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void registerUser(){
        User user = new User();
        user.setUsername("Helison");
        when(userRepository.save(any(User.class))).thenReturn(user);

        var result = userService.registerUser(user);

        assertNotNull(user);
        assertEquals("eu", result.getUsername());

        verify(userRepository, times(1)).save(user);
    }
    @Test
    void listUsers () {
        User user1 = new User();
        user1.setUsername("Helison");
        User user2 = new User();
        user2.setUsername("Pontes");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        var result = userService.listUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Helison", result.get(0).getUsername());
        assertEquals("Pontes", result.get(1).getUsername());
    }
}