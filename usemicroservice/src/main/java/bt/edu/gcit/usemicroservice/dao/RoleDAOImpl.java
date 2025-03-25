package bt.edu.gcit.usemicroservice.dao;

import bt.edu.gcit.usemicroservice.entity.Role;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addRole(Role role) {
        // TODO Auto-generated method
        entityManager.persist(role);
    }
}
