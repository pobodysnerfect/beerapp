package com.pn.beer.entities.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "beer")
public class Beer implements Serializable {

    @Id
    @Column(name = "beer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_id", nullable = false)
    @JsonIgnore
    private Brewery brewery;

    @Column(name = "name")
    private String name;

    @Column(name = "abv")
    private Float abv;

    @Column(name = "og")
    private Integer og;

    @Column(name = "fg")
    private Integer fg;

    @Column(name = "ibu")
    private Integer ibu;

    @Column(name = "favorite")
    private Boolean favorite;

    @Column(name = "style")
    private String style;

    @Column(name = "categories")
    private String categories;

    public Beer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAbv() {
        return abv;
    }

    public void setAbv(Float abv) {
        this.abv = abv;
    }

    public Integer getOg() {
        return og;
    }

    public void setOg(Integer og) {
        this.og = og;
    }

    public Integer getFg() {
        return fg;
    }

    public void setFg(Integer fg) {
        this.fg = fg;
    }

    public Integer getIbu() {
        return ibu;
    }

    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id &&
                Objects.equals(name, beer.name) &&
                Objects.equals(abv, beer.abv) &&
                Objects.equals(og, beer.og) &&
                Objects.equals(fg, beer.fg) &&
                Objects.equals(ibu, beer.ibu) &&
                Objects.equals(favorite, beer.favorite) &&
                Objects.equals(style, beer.style) &&
                Objects.equals(categories, beer.categories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, abv, og, fg, ibu, favorite, style, categories);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abv=" + abv +
                ", og=" + og +
                ", fg=" + fg +
                ", ibu=" + ibu +
                ", favorite=" + favorite +
                ", style='" + style + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }
}
