package bt.edu.gcit.usemicroservice.dao;

import java.util.List;
import bt.edu.gcit.usemicroservice.entity.Country;

public interface CountryDAO {
    Country findByCode(String code);

    // Create
    void save(Country country);

    // Read
    Country findById(Long country_id);

    List<Country> findAll();

    List<Country> findAllByOrderByNameAsc();

    // Update
    void update(Country country);

    // Delete
    void delete(Country country);
}
