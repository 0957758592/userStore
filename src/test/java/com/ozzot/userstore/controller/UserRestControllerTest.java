package com.ozzot.userstore.controller;

import com.ozzot.userstore.entity.User;
import com.ozzot.userstore.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestController userRestController;

    private List<User> users;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        users = new ArrayList<>();
        users.add(new User(1, "name", "email", LocalDate.parse("1111-11-11")));
        users.add(new User(2, "name2", "email2", LocalDate.parse("1111-11-12")));
        users.add(new User(3, "name3", "email3", LocalDate.parse("1111-11-13")));
    }

    @Test
    public void getAll() {
        when(userService.getAll()).thenReturn(users);
        assertEquals(3, users.size());
    }

    @Test
    public void getById() {
        User user = new User(2, "name2", "email2", LocalDate.parse("1111-11-12"));

        when(userService.getById(2)).thenReturn(user);
        assertEquals(2, user.getId());
        assertEquals("name2", user.getName());
    }

    @Test
    public void add() {
        User user = new User(2, "name4", "email4", LocalDate.parse("1111-11-14"));
        userService.addUser(user);
        when(userService.getAll()).thenReturn(users);
        assertEquals(4, users.size());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {

        User user = new User(2, "name4", "email4", LocalDate.parse("1111-11-14"));
        userService.delete(2);
        verify(userService, times(1)).delete(2);
    }
}