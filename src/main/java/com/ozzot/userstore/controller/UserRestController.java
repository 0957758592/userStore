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

    @GetMapping(path = {"/user"})
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = {"/user/{id}"})
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping(path = {"/user/add"})
    public void add(@ModelAttribute User user) {
        userService.addUser(user);
    }

    @PutMapping(path = {"/user/{id}/update"})
    public void update(@ModelAttribute User user,@PathVariable int id) {
        userService.update(user,id);
    }

    @DeleteMapping(path = {"/user/{id}/delete"})
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
}




