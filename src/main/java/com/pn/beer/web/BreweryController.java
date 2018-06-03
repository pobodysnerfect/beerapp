package com.pn.beer.web;

import com.pn.beer.exceptions.NotFoundException;
import com.pn.beer.entities.db.Brewery;
import com.pn.beer.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BreweryController {

    private BreweryRepository breweryRepo;

    @Autowired
    public BreweryController(BreweryRepository breweryRepo) {
        this.breweryRepo = breweryRepo;
    }

    @GetMapping("/breweries")
    public List<Brewery> getAllBreweries() {
        return breweryRepo.findAll();
    }

    @PostMapping("/breweries")
    public Brewery createBrewery(@Valid @RequestBody Brewery brewery) {
        return breweryRepo.save(brewery);
    }

    @GetMapping("/breweries/{breweryId}")
    public Brewery getBrewery(@PathVariable Long breweryId) {
        return breweryRepo.findById(breweryId)
                .orElseThrow(() -> new NotFoundException("BreweryId " + breweryId + " not found"));
    }

    @PutMapping("/breweries/{breweryId}")
    public Brewery updateBrewery(@PathVariable Long breweryId, @Valid @RequestBody Brewery breweryRequest) {
        return breweryRepo.findById(breweryId).map(brewery -> breweryRepo.save(brewery))
                .orElseThrow(() -> new NotFoundException("BreweryId " + breweryId + " not found"));
    }

    @DeleteMapping("/breweries/{breweryId}")
    public ResponseEntity<?> deleteBrewery(@PathVariable Long breweryId) {
        return breweryRepo.findById(breweryId).map(brewery -> {
            breweryRepo.delete(brewery);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("BreweryId " + breweryId + " not found"));
    }
}
