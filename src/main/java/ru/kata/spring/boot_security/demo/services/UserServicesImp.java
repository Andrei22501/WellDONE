package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImp implements UserServices {
    private final UserDao userDao;
    private final RoleServices roleServices;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServicesImp(UserDao userDao, RoleServices roleServices,@Lazy PasswordEncoder passwordEncoder) {
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

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
//    @Override
//    public UserDetails loadUSer(int id) throws UsernameNotFoundException {
//        return userDao.loadUSer(id);
//    }

}