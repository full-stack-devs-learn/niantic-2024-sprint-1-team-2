package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.User;
import com.niantic.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UserController {

    UserDao userDao = new UserDao();

    @GetMapping("/users")
    public String getUsers(Model model) {

        ArrayList<User> users;

        users = userDao.getAllUsers();

        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/users/add")
    public String addUser(Model model) {
        model.addAttribute("users", new User());
        model.addAttribute("action", "add");
        return "users/add_edit";
    }

    @PostMapping("/users/add")
    public String addUser(Model model, @ModelAttribute("users") User user) {
        userDao.addUser(user);
        model.addAttribute("users", user);
        return "users/add_success";
    }


}
