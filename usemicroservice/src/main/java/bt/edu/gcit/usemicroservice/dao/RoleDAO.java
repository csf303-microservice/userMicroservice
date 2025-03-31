package bt.edu.gcit.usemicroservice.dao;

import java.util.List;

import bt.edu.gcit.usemicroservice.entity.Role;

public interface RoleDAO {
    void addRole(Role role);
    List<Role> getAllRoles();
    Role findByID(int id);
    void deleteByID(int id);
}
