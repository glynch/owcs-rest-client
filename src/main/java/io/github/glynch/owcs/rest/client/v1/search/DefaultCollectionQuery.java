package io.github.glynch.owcs.rest.client.v1.search;

public class DefaultCollectionQuery extends AbstractBaseQuery implements CollectionQuery {

    private final Integer limit;
    private final Integer offset;
    private final Boolean totalResults;

    public DefaultCollectionQuery(
            String assetDepth,
            Link[] links,
            String profileName,
            String[] segments,
            Integer limit,
            Integer offset,
            Boolean totalResults) {
        super(assetDepth, links, profileName, segments);
        this.limit = limit;
        this.offset = offset;
        this.totalResults = totalResults;
        if (limit != null) {
            queryParams().put("limit", String.valueOf(limit));
        }
        if (offset != null) {
            queryParams().put("offset", String.valueOf(offset));
        }
        if (totalResults != null) {
            queryParams().put("totalResults", String.valueOf(totalResults));
        }
    }

    @Override
    public Integer limit() {
        return limit;
    }

    @Override
    public Integer offset() {
        return offset;
    }

    @Override
    public Boolean totalResults() {
        return totalResults;
    }

    public static class DefaultCollectionQueryBulder implements CollectionQuery.Builder {

        private String assetDepth;
        private Link[] links;
        private String profileName;
        private String[] segments;
        private Integer limit;
        private Integer offset;
        private Boolean totalResults;

        public DefaultCollectionQueryBulder() {
        }

        @Override
        public Builder assetDepth(String assetDepth) {
            this.assetDepth = assetDepth;
            return this;
        }

        @Override
        public Builder links(Link... links) {
            this.links = links;
            return this;
        }

        @Override
        public Builder profileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        @Override
        public Builder segments(String... segments) {
            this.segments = segments;
            return this;
        }

        @Override
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        @Override
        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        @Override
        public Builder totalResults(Boolean totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        @Override
        public CollectionQuery build() {
            return new DefaultCollectionQuery(assetDepth, links, profileName, segments, limit, offset, totalResults);
        }

    }

}
