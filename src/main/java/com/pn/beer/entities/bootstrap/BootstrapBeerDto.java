package com.pn.beer.entities.bootstrap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = { "OG",  "ABV",  "IBU", "FG" })
public class BootstrapBeerDto {
    private String name;
    private String ABV;
    private int OG;
    private int FG;
    private String style;
    private int IBU;
    private boolean favorite;
    private List<String> category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getABV() {
        return ABV;
    }

    public void setABV(String ABV) {
        this.ABV = ABV;
    }

    public int getOG() {
        return OG;
    }

    public void setOG(int OG) {
        this.OG = OG;
    }

    public int getFG() {
        return FG;
    }

    public void setFG(int FG) {
        this.FG = FG;
    }

    public int getIBU() {
        return IBU;
    }

    public void setIBU(int IBU) {
        this.IBU = IBU;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
