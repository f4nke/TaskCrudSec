package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return (List<User>) entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = get(id);
        entityManager.remove(user);
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }
}