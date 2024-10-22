package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class DefaultCollectionQuery extends AbstractBaseQuery implements CollectionQuery {

    public DefaultCollectionQuery(
            MultiValuedMap<String, String> queryParams) {
        super(queryParams);
    }

    @Override
    public Integer limit() {
        return queryParams().get(LIMIT).stream().findFirst().map(Integer::valueOf).orElse(null);
    }

    @Override
    public Integer offset() {
        return queryParams().get(OFFSET).stream().findFirst().map(Integer::valueOf).orElse(null);
    }

    @Override
    public Boolean totalResults() {
        return queryParams().get(TOTAL_RESULTS).stream().findFirst().map(Boolean::valueOf).orElse(null);
    }

    public static class DefaultCollectionQueryBulder implements CollectionQuery.Builder {

        private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

        public DefaultCollectionQueryBulder() {
        }

        @Override
        public CollectionQuery.Builder assetDepth(int assetDepth) {
            this.queryParams.put(ASSETDEPTH, String.valueOf(assetDepth));
            return this;
        }

        @Override
        public CollectionQuery.Builder all() {
            this.queryParams.put(ASSETDEPTH, "all");
            return this;
        }

        @Override
        public CollectionQuery.Builder links(Link... links) {
            if (links != null && links.length > 0) {
                for (Link link : links) {
                    this.queryParams.put(LINKS, link.toString());
                }
            }
            return this;
        }

        @Override
        public CollectionQuery.Builder profileName(String profileName) {
            this.queryParams.put(PROFILENAME, profileName);
            return this;
        }

        @Override
        public CollectionQuery.Builder segments(String... segments) {
            if (segments != null && segments.length > 0) {
                for (String segment : segments) {
                    this.queryParams.put(SEGMENTS, segment);
                }
            }
            return this;
        }

        @Override
        public CollectionQuery.Builder limit(Integer limit) {
            this.queryParams.put(LIMIT, String.valueOf(limit));
            return this;
        }

        @Override
        public CollectionQuery.Builder offset(Integer offset) {
            this.queryParams.put(OFFSET, String.valueOf(offset));
            return this;
        }

        @Override
        public CollectionQuery.Builder totalResults(Boolean totalResults) {
            this.queryParams.put(TOTAL_RESULTS, String.valueOf(totalResults));
            return this;
        }

        @Override
        public CollectionQuery build() {
            return new DefaultCollectionQuery(queryParams);
        }

    }

}
