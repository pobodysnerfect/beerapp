package com.pn.beer.entities.es;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreweryDocument extends EsDocument {

    @JsonProperty("name")
    private String name;

    public BreweryDocument() {
    }

    public BreweryDocument(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
