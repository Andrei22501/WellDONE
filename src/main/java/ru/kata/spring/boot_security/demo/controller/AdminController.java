package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServices;
import ru.kata.spring.boot_security.demo.services.UserServices;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServices userServices;
    private final RoleServices roleServices;
    @Autowired
    public AdminController(UserServices userServices, RoleServices roleServices) {
        this.userServices = userServices;
        this.roleServices = roleServices;
    }
    @GetMapping("")
    public String userList(Model model){
        List<User> user = userServices.listUsers();
        List<Role> roles = roleServices.listRole();
        model.addAttribute("usersList", user);
        model.addAttribute("roleList", roles);
        return "admin";
    }
    @GetMapping("/new")
    public String userList2(Model model){
        List<Role> roles = roleServices.listRole();
        model.addAttribute("roleList", roles);
        model.addAttribute("NewUser", new User());
        return "newUser";
    }
    @PostMapping("/new")
    public String create(User user){
            userServices.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userServices.delete(id);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServices.show(id));
        model.addAttribute("roleList", roleServices.listRole());
        return "editTable";
    }
    @GetMapping("/{name}")
    public String info(@PathVariable("name") String name, Model model){
        model.addAttribute("info", userServices.showName(name));
        return "index";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServices.update(id, user);
        return "redirect:/admin";
    }
}
