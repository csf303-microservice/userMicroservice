package bt.edu.gcit.usemicroservice.rest;

import bt.edu.gcit.usemicroservice.entity.User;
import bt.edu.gcit.usemicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

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
}
