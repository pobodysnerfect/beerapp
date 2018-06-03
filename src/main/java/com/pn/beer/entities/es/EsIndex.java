package com.pn.beer.entities.es;

public enum EsIndex {
    BREWERY("brewery"),
    BEER("beer");

    private String index;

    EsIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }
}
