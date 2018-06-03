package com.pn.beer.web;

import com.pn.beer.entities.es.EsIndex;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class SearchController {

    private RestHighLevelClient esClient;

    @Autowired
    public SearchController(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }

    @GetMapping("/search")
    public SearchResponse searchByString(@RequestParam("q") String q) throws IOException {
        return esClient.search(buildSearch(q));
    }

    /**
     * Builds search query. This is mostly an example at the moment. Expected to change.
     */
    private SearchRequest buildSearch(String query) {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("name", query));
        searchSourceBuilder.size(10);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.setGlobalText(query);
        searchSourceBuilder.suggest(suggestBuilder);

        searchRequest.indices(EsIndex.BEER.getIndex(), EsIndex.BREWERY.getIndex());
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }


}
