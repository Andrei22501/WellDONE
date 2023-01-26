package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserServices;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllers {
    private final UserServices userServices;

    public RestControllers(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getUsers() {
        List<User> allUsers = userServices.listUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userServices.show(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/admin")
    public ResponseEntity<HttpStatus> create(@RequestBody User user) {
        userServices.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<Void> editUser (@PathVariable("id") int id, @RequestBody User user) {
        userServices.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<User> index(Principal principal) {
        User user = userServices.showEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
