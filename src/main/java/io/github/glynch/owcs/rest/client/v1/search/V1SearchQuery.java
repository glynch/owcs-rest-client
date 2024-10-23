package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;

import io.github.glynch.owcs.rest.client.v1.search.DefaultV1SearchQuery.DefaultV1SearchQueryBuilder;

public interface V1SearchQuery {

    String COUNT_ONLY = "countonly";
    String FIELDS = "fields";
    String LIMIT = "limit";
    String OFFSET = "offset";
    String ORDER_BY = "orderby";
    String LINKS = "links";
    String Q = "q";
    String SEARCH_ENGINE = "searchengine";
    String TOTAL_RESULTS = "totalresults";

    boolean countOnly();

    String[] fields();

    int limit();

    int offset();

    String orderBy();

    String links();

    String q();

    String searchEngine();

    boolean totalResults();

    MultiValuedMap<String, String> queryParams();

    static Builder builder() {
        return new DefaultV1SearchQueryBuilder();
    }

    interface Builder {

        Builder countOnly(boolean countOnly);

        Builder fields(String... fields);

        Builder limit(int limit);

        Builder offset(int offset);

        Builder orderBy(String orderBy);

        Builder links(String links);

        Builder q(String q);

        Builder searchEngine(String searchEngine);

        Builder totalResults(boolean totalResults);

        Builder queryParams(MultiValuedMap<String, String> queryParams);

        V1SearchQuery build();
    }

}
