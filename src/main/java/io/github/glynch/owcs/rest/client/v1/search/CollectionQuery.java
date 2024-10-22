package io.github.glynch.owcs.rest.client.v1.search;

import io.github.glynch.owcs.rest.client.v1.search.DefaultCollectionQuery.DefaultCollectionQueryBulder;

/**
 * See <a href=
 * "https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wcsrt/op-resources-v1-aggregates-sitename-collectionassettype-assetid-items-get.html">REST
 * API</a>
 */
public interface CollectionQuery extends BaseQuery {

    static Builder builder() {
        return new DefaultCollectionQueryBulder();
    }

    Integer limit();

    Integer offset();

    Boolean totalResults();

    interface Builder {

        Builder assetDepth(String assetDepth);

        Builder links(Link... links);

        Builder profileName(String profileName);

        Builder segments(String... segments);

        Builder limit(Integer limit);

        Builder offset(Integer offset);

        Builder totalResults(Boolean totalResults);

        CollectionQuery build();

    }

}
