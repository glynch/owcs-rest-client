package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class DefaultRecommendationQuery extends DefaultCollectionQuery implements RecommendationQuery {

    public DefaultRecommendationQuery(MultiValuedMap<String, String> queryParams) {
        super(queryParams);
    }

    @Override
    public Long visitorId() {
        return queryParams().get(VISITOR_ID).stream().findFirst().map(Long::valueOf).orElse(null);
    }

    public static class DefaultRecommendationQueryBuilder implements RecommendationQuery.Builder {

        CollectionQuery.Builder builder = CollectionQuery.builder();

        private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

        public DefaultRecommendationQueryBuilder() {
        }

        @Override
        public RecommendationQuery.Builder assetDepth(int assetDepth) {
            builder.assetDepth(assetDepth);
            return this;
        }

        @Override
        public RecommendationQuery.Builder all() {
            builder.all();
            return this;
        }

        @Override
        public RecommendationQuery.Builder links(Link... links) {
            builder.links(links);
            return this;
        }

        @Override
        public RecommendationQuery.Builder profileName(String profileName) {
            builder.profileName(profileName);
            return this;
        }

        @Override
        public RecommendationQuery.Builder segments(String... segments) {
            builder.segments(segments);
            return this;
        }

        @Override
        public RecommendationQuery.Builder limit(Integer limit) {
            builder.limit(limit);
            return this;
        }

        @Override
        public RecommendationQuery.Builder offset(Integer offset) {
            builder.offset(offset);
            return this;
        }

        @Override
        public RecommendationQuery.Builder totalResults(Boolean totalResults) {
            builder.totalResults(totalResults);
            return this;
        }

        @Override
        public RecommendationQuery.Builder visitorId(Long visitorId) {
            queryParams.put(VISITOR_ID, String.valueOf(visitorId));
            return this;
        }

        @Override
        public RecommendationQuery build() {
            queryParams.putAll(builder.build().queryParams());
            return new DefaultRecommendationQuery(queryParams);
        }

    }

}
