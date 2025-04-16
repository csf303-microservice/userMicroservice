package bt.edu.gcit.usemicroservice.dao;

import bt.edu.gcit.usemicroservice.entity.Customer;
import java.util.List;
import bt.edu.gcit.usemicroservice.entity.AuthenticationType;

public interface CustomerDAO {
    public Customer findByEMail(String email);

    public Customer findByVerificationCode(String code);

    public void enable(long id);

    Customer registerCustomer(Customer customer);

    boolean isEmailUnique(String email);

    // boolean verify(String code);
    Customer getCustomerById(long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer customer);

    void deleteCustomer(long id);

    void updateAuthenticationType(Long customerId, AuthenticationType type);
}
