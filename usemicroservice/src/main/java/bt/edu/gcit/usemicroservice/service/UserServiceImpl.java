package bt.edu.gcit.usemicroservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import bt.edu.gcit.usemicroservice.dao.UserDAO;
import bt.edu.gcit.usemicroservice.entity.User;
import bt.edu.gcit.usemicroservice.exception.FileSizeException;
import bt.edu.gcit.usemicroservice.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private final String uploadDir = "src/main/resources/static/images";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User findByID(int id) {
        return userDAO.findByID(id);
    }

    @Override
    public void deleteByID(int id) {
        userDAO.deleteByID(id);
    }

    @Override
    @Transactional
    public User updateUser(int id, User updatedUser) {
        // First, find the user by ID
        User existingUser = userDAO.findByID(id);

        // If the user doesn't exist, throw UserNotFoundException
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        // Update the existing user with the data from updatedUser
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());

        // Check if the password has changed. If it has, encode the new password before
        // saving.
        if (!existingUser.getPassword().equals(updatedUser.getPassword())) {

            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setRoles(updatedUser.getRoles());

        // Save the updated user and return it
        return userDAO.save(existingUser);
    }

    @Override
    public boolean isEmailDuplicate(String email) {
        User user = userDAO.findByEmail(email);
        return user != null;
    }

    @Transactional
    @Override
    public void updateUserEnabledStatus(int id, boolean enabled) {
        userDAO.updateUserEnabledStatus(id, enabled);
    }

    @Transactional
    @Override
    public void uploadUserPhoto(int id, MultipartFile photo) throws IOException {
        User user = findByID(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        if (photo.getSize() > 1024 * 1024) {
            throw new FileSizeException("File size must be < 1MB");
        }

        // String filename = StringUtils.cleanPath(photo.getOriginalFilename());
        // Path uploadPath = Paths.get(uploadDir, filename);
        // photo.transferTo(uploadPath);
        // user.setPhoto(filename);
        // save(user);

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Create the directory if it doesn't exist
        }

        // Generate a unique filename
        String originalFilename = StringUtils.cleanPath(photo.getOriginalFilename());
        String filenameExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filenameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String timestamp = String.valueOf(System.currentTimeMillis());
        String filename = filenameWithoutExtension + "_" + timestamp + "." + filenameExtension;

        // Save the file
        Path filePath = uploadPath.resolve(filename);
        photo.transferTo(filePath);

        // Update the user with the photo filename
        user.setPhoto(filename);
        save(user);
    }

}
