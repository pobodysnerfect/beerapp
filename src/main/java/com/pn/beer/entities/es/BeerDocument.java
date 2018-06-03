package com.pn.beer.entities.es;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeerDocument extends EsDocument {

    @JsonProperty("breweryId")
    private Long breweryId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("style")
    private String style;

    @JsonProperty("categories")
    private String categories;


    public BeerDocument() {
        super();
    }

    public BeerDocument(Long id, Long breweryId, String name, String style, String categories) {
        super(id);
        this.breweryId = breweryId;
        this.name = name;
        this.style = style;
        this.categories = categories;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
