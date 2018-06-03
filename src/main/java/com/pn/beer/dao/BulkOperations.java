package com.pn.beer.dao;

import com.pn.beer.entities.db.Brewery;

import java.util.List;

public interface BulkOperations {
    void save(List<Brewery> breweries);
}
