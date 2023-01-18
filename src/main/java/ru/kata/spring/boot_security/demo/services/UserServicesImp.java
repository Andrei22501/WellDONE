package ru.kata.spring.boot_security.demo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServicesImp implements UserServices {
    private final UserDao userDao;
    private final RoleServices roleServices;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServicesImp(UserDao userDao, RoleServices roleServices, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleServices = roleServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User showName(String name) {
        return userDao.showName(name);
    }

    @Override
    public User showEmail(String email) {
        return userDao.showEmail(email);
    }

    @Override
    @Transactional
    public void update(User user) {
        encodePassword(user);
        setRolesForUser(user);
        userDao.update(user);
    }
    @Override
    @Transactional
    public void save(User user) {
        User us = new User();
        encodePassword(user);
        setRolesForUser(user);
        userDao.save(user);
    }
    public void setRolesForUser(User user) {
        user.setRoles(user.getRoles().stream().map(role -> roleServices.showName(role.getName())).collect(Collectors.toSet()));
    }

    @Override
    public User show(int id){
        return userDao.show(id);
    }
    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User auth = userDao.showEmail(username);
        org.hibernate.Hibernate.initialize(auth.getRoles());
        return new org.springframework.security.core.userdetails.User(auth.getEmail(),auth.getPassword(),
                roleAuth(auth.getRoles()));
    }

    private Collection <? extends GrantedAuthority> roleAuth(Set<Role> roles){
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

}