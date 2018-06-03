package com.pn.beer.dao;

import com.pn.beer.entities.db.Brewery;
import com.pn.beer.repositories.BreweryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BulkOperationsDb implements BulkOperations {
    private BreweryRepository breweryRepo;

    public BulkOperationsDb(BreweryRepository breweryRepo) {
        this.breweryRepo = breweryRepo;
    }

    @Override
    public void save(List<Brewery> breweries) {
        Optional.ofNullable(breweries).orElseGet(Collections::emptyList)
                .forEach(breweryRepo::save);
    }
}
