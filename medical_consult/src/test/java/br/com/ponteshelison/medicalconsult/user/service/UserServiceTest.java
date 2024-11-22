package br.com.ponteshelison.medicalconsult.user.service;

import br.com.ponteshelison.medicalconsult.user.Enum.Permission;
import br.com.ponteshelison.medicalconsult.user.domain.User;
import br.com.ponteshelison.medicalconsult.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
        assertEquals("Helison", result.getUsername());

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

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Helison", result.get(0).getUsername()),
                () -> assertEquals("Pontes", result.get(1).getUsername())
        );
    }

    @Test
    void showUser(){
        User user = new User();
        user.setUsername("Helison");

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        var result = userService.showUser(user.getUserId());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Helison", result.getUsername())
        );
    }

    @Test
    void updateUser() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthday = null;
        try {
            birthday = sdf.parse("15/06/1990");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User userToUpdate = new User(1L, "Helison Updated", "updatedemail@example.com", "12345678901", "9876543210", birthday, Permission.ADMIN);

        User existingUser = new User(1L, "Helison", "oldemail@example.com", "12345678901", "9876543210", birthday, Permission.SECRETARY);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(userToUpdate);

        User updatedUser = userService.updateUser(1L, userToUpdate);

        Date finalBirthday = birthday;
        assertAll(
                () -> assertNotNull(updatedUser),
                () -> assertEquals("Helison Updated", updatedUser.getUsername()),
                () -> assertEquals("updatedemail@example.com", updatedUser.getEmail()),
                () -> assertEquals(finalBirthday, updatedUser.getBirthday()),
                () -> assertEquals(Permission.ADMIN, updatedUser.getPermission())
        );
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("Helison");
        user.setUserId(1L);

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getUserId());


        verify(userRepository, times(1)).delete(user);
    }

}