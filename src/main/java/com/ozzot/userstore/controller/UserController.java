package com.ozzot.userstore.controller;

import com.ozzot.userstore.entity.User;
import com.ozzot.userstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getList(Model model) {
        List<User> list = userService.getAll();
        model.addAttribute("users", list);
        return "index";
    }

    @RequestMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "item";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute User user, @PathVariable int id) {
        userService.update(user, id);
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/user";
    }

}

