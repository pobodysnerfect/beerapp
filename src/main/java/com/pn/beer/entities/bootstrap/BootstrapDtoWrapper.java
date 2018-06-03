package com.pn.beer.entities.bootstrap;

import java.util.List;

/**
 * These classes and sub classes match the json structure of the data.
 */
public class BootstrapDtoWrapper {
    private List<BootstrapBreweryDto> breweries;

    public List<BootstrapBreweryDto> getBreweries() {
        return breweries;
    }

    public void setBreweries(List<BootstrapBreweryDto> breweries) {
        this.breweries = breweries;
    }
}
