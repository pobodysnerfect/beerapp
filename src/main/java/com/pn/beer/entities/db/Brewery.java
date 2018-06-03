package com.pn.beer.entities.db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "brewery")
public class Brewery implements Serializable {

    @Id
    @Column(name = "brewery_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "brewery")
    private Set<Beer> beers = new HashSet<>();

    public Brewery() { }

    public Brewery(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Beer> getBeers() {
        return beers;
    }

    public void setBeers(Set<Beer> beers) {
        this.beers = beers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brewery brewery = (Brewery) o;
        return id == brewery.id &&
                Objects.equals(name, brewery.name) &&
                Objects.equals(address, brewery.address) &&
                Objects.equals(beers, brewery.beers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, beers);
    }

    @Override
    public String toString() {
        return "Brewery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", beers=" + beers +
                '}';
    }
}
