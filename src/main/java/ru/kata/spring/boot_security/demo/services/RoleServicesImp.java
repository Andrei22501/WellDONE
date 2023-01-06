package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServicesImp implements RoleServices {
    private final RoleDao roleDao;

    public RoleServicesImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public Role show(int id) {
        return roleDao.show(id);
    }
    @Override
    public Role showName(String name){
        return roleDao.showName(name);
    }
    @Transactional
    @Override
    public void save(Role role) {
    roleDao.save(role);
    }
}
