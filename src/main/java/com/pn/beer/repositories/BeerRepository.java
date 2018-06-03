package com.pn.beer.repositories;

import com.pn.beer.entities.db.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long> {
    List<Beer> findByBreweryId(Long breweryId);
}
