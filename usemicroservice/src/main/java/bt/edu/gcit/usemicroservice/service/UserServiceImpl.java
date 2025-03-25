package bt.edu.gcit.usemicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bt.edu.gcit.usemicroservice.dao.UserDAO;
import bt.edu.gcit.usemicroservice.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDAO.save(user);
    }
}
