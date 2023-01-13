package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServices;
import ru.kata.spring.boot_security.demo.services.UserServices;

import java.security.Principal;
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
    @GetMapping()
    public String userList( Model model, Principal principal){
        model.addAttribute("info", userServices.showName(principal.getName()));
        model.addAttribute("usersList", userServices.listUsers());
        model.addAttribute("roleList", roleServices.listRole());
        model.addAttribute("NewUser", new User());
        return "admin";
    }
    @PatchMapping ("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userServices.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServices.delete(id);
        return "redirect:/admin";
    }

    @PostMapping()
    public String create(User user) {
        userServices.save(user);
        return "redirect:/admin";
    }
//        return "index";
//        model.addAttribute("info", userServices.showName(authentication.getName()));
//    public String info(@PathVariable("name") String name,Authentication authentication, Model model){
//    @GetMapping("/admin")
//    }
//        return "editTable";
//        model.addAttribute("roleList", roleServices.listRole());
//        model.addAttribute("user", userServices.show(id));
//    public String edit(@PathVariable("id") int id, Model model) {
//    @PatchMapping("/{id}")
//    @GetMapping("")
//    public String userList2(Model model){
//        List<Role> roles = roleServices.listRole();
//        model.addAttribute("roleList", roles);
//        model.addAttribute("NewUser", new User());
//        return "admin";
//    }
//    }
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userServices.update(user);
//        return "redirect:/admin";
//    }
}
