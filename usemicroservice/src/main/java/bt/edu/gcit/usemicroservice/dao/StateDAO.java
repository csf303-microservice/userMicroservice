package bt.edu.gcit.usemicroservice.dao;

import bt.edu.gcit.usemicroservice.entity.Country;
import bt.edu.gcit.usemicroservice.entity.State;
import java.util.List;

public interface StateDAO {
    List<State> findByCountryOrderByNameAsc(Country country);

    List<State> findAll();

    State findById(int theId);

    void save(State theState, Long country_id);

    void deleteById(int theId);

    List<State> listStatesByCountry(Long countryId);
}
