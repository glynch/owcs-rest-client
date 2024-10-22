package io.github.glynch.owcs.rest.client.v1.search;

import io.github.glynch.owcs.rest.client.v1.search.DefaultRecommendationQuery.DefaultRecommendationQueryBuilder;

public interface RecommendationQuery extends CollectionQuery {

    static Builder builder() {
        return new DefaultRecommendationQueryBuilder();
    }

    Long visitorId();

    interface Builder {
        Builder assetDepth(String assetDepth);

        Builder links(Link... links);

        Builder profileName(String profileName);

        Builder segments(String... segments);

        Builder limit(Integer limit);

        Builder offset(Integer offset);

        Builder totalResults(Boolean totalResults);

        Builder visitorId(Long visitorId);

        RecommendationQuery build();
    }

}
