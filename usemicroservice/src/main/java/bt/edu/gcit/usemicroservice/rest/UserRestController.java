package bt.edu.gcit.usemicroservice.rest;

import bt.edu.gcit.usemicroservice.entity.User;
import bt.edu.gcit.usemicroservice.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User save(@RequestBody User user) {
        System.out.println(user);
        return userService.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User findByID(@PathVariable int id) {
        return userService.findByID(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable int id) {
        userService.deleteByID(id);
        return ResponseEntity.ok("Deleted user with ID: " + id);
    }
}