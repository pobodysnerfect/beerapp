package com.pn.beer.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pn.beer.dao.BulkOperations;
import com.pn.beer.entities.GenericFactory;
import com.pn.beer.entities.bootstrap.BootstrapDtoWrapper;
import com.pn.beer.entities.db.Brewery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This reads json file data and saves it to the database.
 */
public class BootstrapDataInjector {

    private static Logger LOG = LoggerFactory.getLogger(BootstrapDataInjector.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static TypeReference<BootstrapDtoWrapper> typeReference = new TypeReference<BootstrapDtoWrapper>() {
    };

    private ResourceLoader resourceLoader;
    private List<BulkOperations> bulkOperations;

    public BootstrapDataInjector(ResourceLoader resourceLoader, List<BulkOperations> bulkOperations) {
        this.resourceLoader = resourceLoader;
        this.bulkOperations = bulkOperations;
    }

    @PostConstruct
    public void injectDataFromFile() {
        Resource resource = resourceLoader.getResource("classpath:beer_data.json");
        try {
            final BootstrapDtoWrapper data = objectMapper.readValue(resource.getInputStream(), typeReference);
            final List<Brewery> breweries = data.getBreweries().stream()
                    .map(GenericFactory::breweryFrom)
                    .collect(Collectors.toList());

            bulkOperations.forEach(bulkOperations -> bulkOperations.save(breweries));
            LOG.info("Breweries and Beers were successfully read. Breweries ({}) ", data.getBreweries().size());
        } catch (IOException ex) {
            LOG.error("Exception while reading json bootstrap data", ex);
        }
    }
}
