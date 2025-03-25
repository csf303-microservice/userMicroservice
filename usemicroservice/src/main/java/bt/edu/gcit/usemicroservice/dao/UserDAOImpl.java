package bt.edu.gcit.usemicroservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository; 
import bt.edu.gcit.usemicroservice.entity.User;
import jakarta.persistence.EntityManager;

@Repository 
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        return entityManager.merge(user);
    }
}
