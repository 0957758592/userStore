package com.ozzot.userstore.web;

import com.ozzot.userstore.entity.User;
import com.ozzot.userstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String getList(Model model) {
        return getAllUsers(model);
    }

    @RequestMapping("/user/{id}")
    public String getById(@PathVariable int id, Model model) {
        return getUser(id, model);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
//        return "redirect:/user";
    }

//    @RequestMapping(value = "user/add", method = RequestMethod.POST)
//    public String create(Model model){
//        model.addAttribute("user", new User());
//        return "create";
//    }

    @RequestMapping(value = "/user/{id}/update", method = RequestMethod.PUT)
    public String update(@PathVariable int id) {
        userService.update(id);
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/user";
    }

    private String getAllUsers(Model model) {
        List<User> list = userService.getAll();
        model.addAttribute("users", list);
        return "index";
    }

    private String getUser(int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("users", user);
        return "item";
    }

}

