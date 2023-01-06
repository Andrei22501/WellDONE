package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> listRole();

    Role show(int id);

    Role showName(String name);

    void save(Role role);
}
