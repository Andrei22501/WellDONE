package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String e = "from User";
        TypedQuery<User> query = entityManager.createQuery(e, User.class);
        return query.getResultList();
    }

    @Override
    public User show(int id) {
        String e = "from User where id =" + id;
        TypedQuery<User> query = entityManager.createQuery(e, User.class);
        return query.getSingleResult();
    }

    @Override
    public User showName(String name) {
        return entityManager.createQuery("from User where name = ?1", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }
    @Override
    public User showEmail(String email) {
        return entityManager.createQuery("from User where email = ?1", User.class)
                .setParameter(1, email)
                .getSingleResult();
    }
    @Override
    public void update(int id, User user) {
        User userUpdate = show(id);
        userUpdate.setName(user.getName());
        userUpdate.setLastname(user.getLastname());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setRoles(user.getRoles());
    }
    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}