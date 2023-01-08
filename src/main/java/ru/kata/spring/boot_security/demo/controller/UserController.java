package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.RoleServices;
import ru.kata.spring.boot_security.demo.services.UserServices;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;
    private final RoleServices roleServices;

    public UserController(UserServices userServices, RoleServices roleServices) {
        this.userServices = userServices;
        this.roleServices = roleServices;
    }
    @GetMapping("/{name}")
    public String info(@PathVariable("name") String name, Model model){
        model.addAttribute("info", userServices.showName(name));
        return "user";
    }
}
