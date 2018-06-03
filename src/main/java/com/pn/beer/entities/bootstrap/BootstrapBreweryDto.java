package com.pn.beer.entities.bootstrap;

import java.util.List;

public class BootstrapBreweryDto {
    private String name;
    private String info;
    private List<BootstrapBeerDto> beers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<BootstrapBeerDto> getBeers() {
        return beers;
    }

    public void setBeers(List<BootstrapBeerDto> beers) {
        this.beers = beers;
    }
}
