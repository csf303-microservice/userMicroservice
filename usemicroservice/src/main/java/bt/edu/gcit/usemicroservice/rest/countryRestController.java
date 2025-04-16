package bt.edu.gcit.usemicroservice.rest;

import bt.edu.gcit.usemicroservice.entity.Country;
import bt.edu.gcit.usemicroservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class countryRestController {
    private CountryService countryService;

    @Autowired
    public countryRestController(CountryService theCountryService) {
        countryService = theCountryService;
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        countryService.save(country);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable int id) {
        Country country = countryService.findById((long) id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<Country>> getAllCountriesOrdered() {
        List<Country> countries = countryService.findAllByOrderByNameAsc();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        countryService.update(country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCountry(@RequestBody Country country) {
        countryService.delete(country);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
