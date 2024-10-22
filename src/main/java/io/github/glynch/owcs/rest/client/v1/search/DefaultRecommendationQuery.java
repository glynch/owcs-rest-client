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

        private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

        public DefaultRecommendationQueryBuilder() {
        }

        @Override
        public RecommendationQuery.Builder assetDepth(int assetDepth) {
            this.queryParams.put(ASSETDEPTH, String.valueOf(assetDepth));
            return this;
        }

        @Override
        public RecommendationQuery.Builder all() {
            this.queryParams.put(ASSETDEPTH, "all");
            return this;
        }

        @Override
        public RecommendationQuery.Builder links(Link... links) {
            if (links != null && links.length > 0) {
                for (Link link : links) {
                    this.queryParams.put(LINKS, link.toString());
                }
            }
            return this;
        }

        @Override
        public RecommendationQuery.Builder profileName(String profileName) {
            this.queryParams.put(PROFILENAME, profileName);
            return this;
        }

        @Override
        public RecommendationQuery.Builder segments(String... segments) {
            if (segments != null && segments.length > 0) {
                for (String segment : segments) {
                    this.queryParams.put(SEGMENTS, segment);
                }
            }
            return this;
        }

        @Override
        public RecommendationQuery.Builder limit(Integer limit) {
            this.queryParams.put(LIMIT, String.valueOf(limit));
            return this;
        }

        @Override
        public RecommendationQuery.Builder offset(Integer offset) {
            this.queryParams.put(OFFSET, String.valueOf(offset));
            return this;
        }

        @Override
        public RecommendationQuery.Builder totalResults(Boolean totalResults) {
            this.queryParams.put(TOTAL_RESULTS, String.valueOf(totalResults));
            return this;
        }

        @Override
        public RecommendationQuery.Builder visitorId(Long visitorId) {
            this.queryParams.put(VISITOR_ID, String.valueOf(visitorId));
            return this;
        }

        @Override
        public RecommendationQuery build() {
            return new DefaultRecommendationQuery(queryParams);
        }

    }

}
