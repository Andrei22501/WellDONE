package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();
    User show(int id);
    User showName(String name);

    User showEmail(String email);

    void update(User user);

    void delete(int id);

    void save(User user);
}
