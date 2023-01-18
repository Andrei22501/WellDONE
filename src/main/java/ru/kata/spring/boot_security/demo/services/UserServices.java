package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserServices extends UserDetailsService {
    List<User> listUsers();
    User showName(String name);

    User showEmail(String email);

    @Transactional
    void update(User user);
    @Transactional
    void save(User user);
    User show(int id);
    @Transactional
    void delete(int id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
