package com.pn.beer.repositories;

import com.pn.beer.entities.db.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
