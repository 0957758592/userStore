package com.ozzot.userstore.controller;

import com.ozzot.userstore.entity.User;
import com.ozzot.userstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController{

    @Autowired
    UserService userService;

    @GetMapping(path = {"/users"})
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = {"/{id}/user"})
    public User getById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @PostMapping(path = {"/add"})
    public void add(@ModelAttribute User user) {
        userService.addUser(user);
    }

    @PutMapping(path = {"/{id}/user"})
    public void update(@ModelAttribute User user,@PathVariable("id") int id) {
        userService.update(user,id);
    }

    @DeleteMapping(path = {"/user/{id}"})
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }
}




