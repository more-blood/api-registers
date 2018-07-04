package com.blood.registers.service;

import static org.junit.Assert.*;

import com.blood.registers.model.User;
import com.blood.registers.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceConfigurationContext {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @MockBean
    private UserRepository repository;

    @Autowired
    private UserService service;

    private List<User> users = new ArrayList<>();
    private User user;
    private Long id = Long.valueOf(1);

    @Before
    public void setup() {
        user = User.builder()
                .id(id)
                .name("Raphael")
                .lastName("Freitas")
                .build();

        users.add(user);

        Mockito.when(repository.findAll()).thenReturn(users);
        Mockito.when(repository.findById((long) 1)).thenReturn(Optional.of(user));
        Mockito.when(repository.findById((long) 2)).thenReturn(Optional.empty());
    }

    @Test
    public void create() throws Exception {
        Mockito.when(repository.save(user)).thenReturn(user);
        User response = service.create(user);
        assertEquals(response.getId(), id);
    }


    @Test
    public void findAll() {
        List<User> users = service.findAll();
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getName(), "Raphael");
    }

    @Test
    public void findOne() {
        Optional<User> response = service.finOne((long) 1);
        assertTrue(response.isPresent());
        assertEquals(response.get(), user);
    }

    @Test
    public void findOneNotFound() {
        Optional<User> response = service.finOne((long) 2);
        assertTrue(!response.isPresent());
    }

    @Test
    public void update() {

        User userUpdate = User.builder()
                .id((long) 2)
                .name("Raphael")
                .lastName("Freitas")
                .build();

        Mockito.when(repository.save(userUpdate)).thenReturn(userUpdate);

        User response = service.update((long) 2, userUpdate);
        assertEquals(response, userUpdate);
    }

    @Test
    public void remove() {
        service.remove(id);
    }

}
