package com.pn.beer.entities;

import com.pn.beer.entities.bootstrap.BootstrapBeerDto;
import com.pn.beer.entities.bootstrap.BootstrapBreweryDto;
import com.pn.beer.entities.db.Beer;
import com.pn.beer.entities.db.Brewery;
import com.pn.beer.entities.es.BeerDocument;
import com.pn.beer.entities.es.BreweryDocument;
import org.springframework.lang.NonNull;

import java.util.Objects;

public class GenericFactory {

    /**
     * Maps a BootstrapBeerDto to a Entity object which can be saved in the db.
     *
     * @param beerDto beer dto from json file
     * @return A beer entity which can be saved in the db
     * @throws NullPointerException if beerDto is null
     */
    private static Beer beerFrom(@NonNull BootstrapBeerDto beerDto) {
        Objects.requireNonNull(beerDto);

        Beer beer = new Beer();
        beer.setOg(beerDto.getOG());
        beer.setName(beerDto.getName());
        beer.setFg(beerDto.getFG());
        beer.setIbu(beerDto.getIBU());
        beer.setFavorite(beerDto.isFavorite());
        beer.setStyle(beerDto.getStyle());
        beer.setCategories(String.join(",", beerDto.getCategory()));
        return beer;
    }

    /**
     * Builds an elastic search document from the provided database entity.
     *
     * @param beer the brewery entity
     * @return elastic search document
     * @throws NullPointerException if brewery is null
     */
    public static BeerDocument beerDocFrom(@NonNull Beer beer) {
        Objects.requireNonNull(beer);

        final BeerDocument beerDocument = new BeerDocument();
        beerDocument.setId(beer.getId());
        beerDocument.setBreweryId(beer.getBrewery().getId());
        beerDocument.setName(beer.getName());
        beerDocument.setStyle(beer.getStyle());
        beerDocument.setCategories(beer.getCategories());
        return beerDocument;
    }

    /**
     * builds a brewery and any beers which belong to the brewery from the
     * bootstrap dto.
     *
     * @param breweryDto bootstrap dto (data injected at bootstrap)
     * @return the brewery database entity
     * @throws NullPointerException if breweryDto is null
     */
    public static Brewery breweryFrom(@NonNull BootstrapBreweryDto breweryDto) {
        Objects.requireNonNull(breweryDto);

        final Brewery brewery = new Brewery(breweryDto.getName(), breweryDto.getInfo());

        breweryDto.getBeers().stream()
                .map(GenericFactory::beerFrom)
                .forEach(beer -> {
                    beer.setBrewery(brewery);
                    brewery.getBeers().add(beer);
                });

        return brewery;
    }

    /**
     * Builds an elastic search document from the provided database entity.
     *
     * @param brewery the brewery entity
     * @return elastic search document
     * @throws NullPointerException if brewery is null
     */
    public static BreweryDocument breweryDocFrom(@NonNull Brewery brewery) {
        Objects.requireNonNull(brewery);

        final BreweryDocument breweryDocument = new BreweryDocument();
        breweryDocument.setId(brewery.getId());
        breweryDocument.setName(brewery.getName());
        return breweryDocument;
    }
}
