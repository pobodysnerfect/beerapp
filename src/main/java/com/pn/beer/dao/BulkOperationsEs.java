package com.pn.beer.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pn.beer.entities.GenericFactory;
import com.pn.beer.entities.db.Brewery;
import com.pn.beer.entities.es.EsDocument;
import com.pn.beer.entities.es.EsIndex;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.pn.beer.entities.GenericFactory.breweryDocFrom;

public class BulkOperationsEs implements BulkOperations {

    private static Logger LOG = LoggerFactory.getLogger(BulkOperationsEs.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private RestHighLevelClient esClient;

    public BulkOperationsEs(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }

    @Override
    public void save(List<Brewery> breweries) {
        final BulkRequest request = new BulkRequest();

        Optional.ofNullable(breweries).orElseGet(Collections::emptyList)
                .stream()
                .flatMap(this::createIndexRequests)
                .forEach(request::add);

        try {
            esClient.bulk(request);
        } catch (IOException ex) {
            LOG.error("Exception while reading json bootstrap data", ex);
        }
    }

    /**
     * Creates all index requests for Brewery and Beer
     *
     * @param brewery brewery
     * @return a stream of index requests
     */
    private Stream<IndexRequest> createIndexRequests(Brewery brewery) {
        Objects.requireNonNull(brewery);

        final List<IndexRequest> requests = new ArrayList<>();

        Optional.of(breweryDocFrom(brewery))
                .map(breweryDoc -> createIndex(breweryDoc, EsIndex.BREWERY))
                .ifPresent(requests::add);

        Optional.ofNullable(brewery.getBeers()).orElseGet(Collections::emptySet).stream()
                .map(GenericFactory::beerDocFrom)
                .map(beerDoc -> createIndex(beerDoc, EsIndex.BEER))
                .forEach(requests::add);

        return requests.stream();
    }

    /**
     * Creates index requests
     */
    private IndexRequest createIndex(EsDocument doc, EsIndex index) {

        try {
            return new IndexRequest(index.getIndex(), "doc", String.valueOf(doc.getId()))
                    .source(objectMapper.writeValueAsBytes(doc), XContentType.JSON);
        } catch (JsonProcessingException ex) {
            LOG.error("could not write object to json for converting to elastic search document", ex);
            return null;
        }
    }
}
