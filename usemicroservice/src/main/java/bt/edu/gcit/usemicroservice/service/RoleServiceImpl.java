package bt.edu.gcit.usemicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bt.edu.gcit.usemicroservice.dao.RoleDAO;
import bt.edu.gcit.usemicroservice.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        // TODO Auto-generated method stub
        roleDAO.addRole(role);
    }
}
