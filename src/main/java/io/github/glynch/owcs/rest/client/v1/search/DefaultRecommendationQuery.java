package io.github.glynch.owcs.rest.client.v1.search;

public class DefaultRecommendationQuery extends DefaultCollectionQuery implements RecommendationQuery {

    private final Long visitorId;

    public DefaultRecommendationQuery(String assetDepth, Link[] links, String profileName, String[] segments,
            Integer limit,
            Integer offset, Boolean totalResults, Long visitorId) {
        super(assetDepth, links, profileName, segments, limit, offset, totalResults);
        this.visitorId = visitorId;
        if (visitorId != null) {
            queryParams().put("visitorId", String.valueOf(visitorId));
        }
    }

    @Override
    public Long visitorId() {
        return visitorId;
    }

    public static class DefaultRecommendationQueryBuilder implements RecommendationQuery.Builder {

        private String assetDepth;
        private Link[] links;
        private String profileName;
        private String[] segments;
        private Integer limit;
        private Integer offset;
        private Boolean totalResults;
        private Long visitorId;

        public DefaultRecommendationQueryBuilder() {
        }

        @Override
        public RecommendationQuery.Builder assetDepth(String assetDepth) {
            this.assetDepth = assetDepth;
            return this;
        }

        @Override
        public RecommendationQuery.Builder links(Link... links) {
            this.links = links;
            return this;
        }

        @Override
        public RecommendationQuery.Builder profileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        @Override
        public RecommendationQuery.Builder segments(String... segments) {
            this.segments = segments;
            return this;
        }

        @Override
        public RecommendationQuery.Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        @Override
        public RecommendationQuery.Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        @Override
        public RecommendationQuery.Builder totalResults(Boolean totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        @Override
        public RecommendationQuery.Builder visitorId(Long visitorId) {
            this.visitorId = visitorId;
            return this;
        }

        @Override
        public RecommendationQuery build() {
            return new DefaultRecommendationQuery(assetDepth, links, profileName, segments, limit, offset, totalResults,
                    visitorId);
        }

    }

}
