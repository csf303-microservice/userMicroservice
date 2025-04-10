package bt.edu.gcit.usemicroservice.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails login(String email, String password);
}
