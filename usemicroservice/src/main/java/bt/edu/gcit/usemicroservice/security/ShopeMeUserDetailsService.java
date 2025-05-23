package bt.edu.gcit.usemicroservice.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bt.edu.gcit.usemicroservice.dao.UserDAO;
import bt.edu.gcit.usemicroservice.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import bt.edu.gcit.usemicroservice.security.ShopmeuserDetails;

import bt.edu.gcit.usemicroservice.dao.CustomerDAO; // import the new DAO for Customer entities 
import bt.edu.gcit.usemicroservice.entity.Customer; // import the new Customer entity 
import java.util.Collections; // import the Collections class 

@Service
public class ShopeMeUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserDAO userDAO;
    private final CustomerDAO customerDAO; // new DAO for Customer entities

    @Autowired
    public ShopeMeUserDetailsService(UserDAO userDAO, CustomerDAO customerDAO) {
        this.userDAO = userDAO;
        this.customerDAO = customerDAO; // initialize the new DAO
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            // List<GrantedAuthority> authorities = user.getRoles().stream()
            //         .map(role -> new SimpleGrantedAuthority(role.getName()))
            //         .collect(Collectors.toList());
            // return new org.springframework.security.core.userdetails.User(user.getEmail(),
            //         user.getPassword(), authorities);
            return new ShopmeuserDetails(user);
        }

        Customer customer = customerDAO.findByEMail(email); // try to load a Customer if no User was found
        if (customer != null) {
            // since Customer entities don't have authorities, we just pass an empty list of
            // authorities
            return new org.springframework.security.core.userdetails.User(customer.getEmail(),
                    customer.getPassword(), Collections.emptyList());
        }

        throw new UsernameNotFoundException("User not found with email: " + email);

        // Old Code
        // System.out.println("Email: " + email); // print out the email
        // User user = userDAO.findByEmail(email);
        // if (user == null) {
        // throw new UsernameNotFoundException("User not found with email: " + email);
        // }

        // // System.out.println("User: " + user.getEmail()); // print out the user
        // email
        // List<GrantedAuthority> authorities = user.getRoles().stream()
        // .map(role -> {
        // System.out.println("Role: " + role.getName()); // print out the role name
        // return new SimpleGrantedAuthority(role.getName());
        // })
        // .collect(Collectors.toList());

        // System.out.println("Authorities: " + authorities); // print out the list of
        // authorities
        // System.out.println("User in loadbyUserna: " + user.getPassword()); // print
        // out the user email
        // return new
        // org.springframework.security.core.userdetails.User(user.getEmail(),
        // user.getPassword(), authorities);
    }
}
