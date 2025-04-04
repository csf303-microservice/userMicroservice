package bt.edu.gcit.usemicroservice.dao;

import java.util.List;

import bt.edu.gcit.usemicroservice.entity.User;

public interface UserDAO {
    User save(User user);
    List<User> getAllUsers();
    User findByID(int id);
    void deleteByID(int id);
    boolean isEmailDuplicate(String email);
    User updateUser(int id,User user);
    void updateUserEnabledStatus(int id, boolean enabled);
    User findByEmail(String email);
}
