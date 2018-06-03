package com.pn.beer.bootstrap;

import com.pn.beer.dao.BulkOperations;
import com.pn.beer.dao.BulkOperationsDb;
import com.pn.beer.dao.BulkOperationsEs;
import com.pn.beer.repositories.BreweryRepository;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@Configuration
public class BootstrapConfig {

    @Bean
    public BootstrapDataInjector dataBootstrap(ResourceLoader resourceLoader,
                                               List<BulkOperations> brewerySavers) {
        return new BootstrapDataInjector(resourceLoader, brewerySavers);
    }

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient () {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    @Bean
    public BulkOperations saveBreweriesDb(BreweryRepository breweryRepo) {
        return new BulkOperationsDb(breweryRepo);
    }

    @Bean
    public BulkOperations saveBreweriesEs() {
        return new BulkOperationsEs(restHighLevelClient());
    }
}
