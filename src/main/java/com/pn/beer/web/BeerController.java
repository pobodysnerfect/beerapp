package com.pn.beer.web;

import com.pn.beer.exceptions.NotFoundException;
import com.pn.beer.entities.db.Beer;
import com.pn.beer.repositories.BeerRepository;
import com.pn.beer.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BeerController {

    private BeerRepository beerRepo;
    private BreweryRepository breweryRepo;

    @Autowired
    public BeerController(BeerRepository beerRepo, BreweryRepository breweryRepo) {
        this.beerRepo = beerRepo;
        this.breweryRepo = breweryRepo;
    }

    @GetMapping("/beers")
    public List<Beer> getAllBeers() {
        return beerRepo.findAll();
    }

    @GetMapping("/brewery/{breweryId}/beers")
    public List<Beer> getAllBeersByBreweryId(@PathVariable (value = "breweryId") Long breweryId) {
        return beerRepo.findByBreweryId(breweryId);
    }

    @PostMapping("/brewery/{breweryId}/beers")
    public Beer createBeer(@PathVariable (value = "breweryId") Long breweryId,
                                 @Valid @RequestBody Beer beer) {
        return breweryRepo.findById(breweryId).map(brewery -> {
            beer.setBrewery(brewery);
            return beerRepo.save(beer);
        }).orElseThrow(() -> new NotFoundException("PostId " + breweryId + " not found"));
    }

    @GetMapping("/beers/{beerId}")
    public Beer getBeer(@PathVariable (value = "beerId") Long beerId) {
        return beerRepo.findById(beerId)
                .orElseThrow(() -> new NotFoundException("BeerId " + beerId + "not found"));
    }

    @PutMapping("/brewery/{breweryId}/beers/{beerId}")
    public Beer updateBeer(@PathVariable (value = "breweryId") Long breweryId,
                                 @PathVariable (value = "beerId") Long beerId,
                                 @Valid @RequestBody Beer beerRequest) {
        requireExistence(breweryId);

        return beerRepo.findById(beerId).map(beer -> {
            //beer.setText(beerRequest.getText());
            return beerRepo.save(beer);
        }).orElseThrow(() -> new NotFoundException("BeerId " + beerId + "not found"));
    }

    @DeleteMapping("/posts/{breweryId}/beers/{beerId}")
    public ResponseEntity<?> deleteBeer(@PathVariable (value = "breweryId") Long breweryId,
                                        @PathVariable (value = "beerId") Long beerId) {
        requireExistence(breweryId);

        return beerRepo.findById(beerId).map(beer -> {
            beerRepo.delete(beer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("BeerId " + beerId + " not found"));
    }

    private void requireExistence(long breweryId) {
        if(!breweryRepo.existsById(breweryId)) {
            throw new NotFoundException("PostId " + breweryId + " not found");
        }
    }
}
