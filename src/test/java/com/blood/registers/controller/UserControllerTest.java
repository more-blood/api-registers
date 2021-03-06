package com.blood.registers.controller;

import com.blood.registers.model.User;
import com.blood.registers.service.UserService;
import com.blood.registers.utils.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(MockitoJUnitRunner.class)
@ComponentScan("com.blood.registers.controller")
public class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jsonUser;

    @Before
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void create() throws Exception {
        User user = User.builder()
                .name("Raphael")
                .lastName("Freitas").build();

        Mockito.when(userService.create(user)).thenReturn(user);

        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser.write(user).getJson()))
            .andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void createException() throws Exception {
        User user = User.builder()
                .name("Raphael")
                .lastName("Freitas").build();

        Mockito.when(userService.create(user)).thenThrow(CustomException.class);

        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser.write(user).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    public void findAll() throws Exception {
        User user = User.builder()
                .name("Freitas")
                .lastName("santos").build();

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userService.findAll())
                .thenReturn(users);

        MockHttpServletResponse response = mvc
                .perform(get("/api/v1/user")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void findOne() throws Exception {
        Long id = Long.valueOf(1);
        User user = User.builder()
                .id(id)
                .name("Raphael")
                .lastName("Freitas").build();

        Mockito.when(userService.finOne(id)).thenReturn(Optional.of(user));

        MockHttpServletResponse response = mvc
                .perform(get("/api/v1/user/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void update() throws Exception {

        Long id = Long.valueOf(1);
        User user = User.builder()
                .id(id)
                .name("Raphael")
                .lastName("Freitas").build();

        Mockito.when(userService.update(id, user)).thenReturn(user);

        MockHttpServletResponse response = mvc
                .perform(put("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser.write(user).getJson()))
            .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void remove() throws Exception {
        Long id = Long.valueOf(1);

        MockHttpServletResponse response = mvc
                .perform(delete("/api/v1/user/1"))
            .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}


