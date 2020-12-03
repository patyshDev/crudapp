package ru.snakesnake.crudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.snakesnake.crudapp.model.User;
import ru.snakesnake.crudapp.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "all-users";
    }
    @GetMapping("/add")
    public String getUserForm() {
        return "user-form";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute("addUser") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("editUser", userService.getUserById(id));
        return "user-edit-form";
    }
    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("editUser") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

}
