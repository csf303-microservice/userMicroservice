package bt.edu.gcit.usemicroservice.service;

import java.util.List;

import bt.edu.gcit.usemicroservice.entity.Role;

public interface RoleService {
    void addRole(Role role);
    List<Role> getAllRoles();
    Role findByID(int id);
    void deleteByID(int id);
}