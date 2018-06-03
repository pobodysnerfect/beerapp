package com.pn.beer.entities.es;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EsDocument {
    @JsonProperty("id")
    private Long id;

    public EsDocument() {
    }

    public EsDocument(Long idx) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
