package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class DefaultV1SearchQuery implements V1SearchQuery {

    private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

    public DefaultV1SearchQuery(MultiValuedMap<String, String> queryParams) {
        this.queryParams.putAll(queryParams);
    }

    @Override
    public boolean countOnly() {
        throw new UnsupportedOperationException("Unimplemented method 'fields'");
    }

    @Override
    public String[] fields() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fields'");
    }

    @Override
    public int limit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'limit'");
    }

    @Override
    public int offset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'offset'");
    }

    @Override
    public String orderBy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderBy'");
    }

    @Override
    public String links() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'links'");
    }

    @Override
    public String q() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'q'");
    }

    @Override
    public String searchEngine() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchEngine'");
    }

    @Override
    public boolean totalResults() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'totalResults'");
    }

    @Override
    public MultiValuedMap<String, String> queryParams() {
        return queryParams;
    }

    public static class DefaultV1SearchQueryBuilder implements V1SearchQuery.Builder {

        private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

        public DefaultV1SearchQueryBuilder() {
        }

        @Override
        public Builder countOnly(boolean countOnly) {
            queryParams.put(COUNT_ONLY, String.valueOf(countOnly));
            return this;
        }

        @Override
        public Builder fields(String... fields) {
            for (String field : fields) {
                queryParams.put(FIELDS, field);
            }
            return this;
        }

        @Override
        public Builder limit(int limit) {
            queryParams.put(LIMIT, String.valueOf(limit));
            return this;
        }

        @Override
        public Builder offset(int offset) {
            queryParams.put(OFFSET, String.valueOf(offset));
            return this;
        }

        @Override
        public Builder orderBy(String orderBy) {
            queryParams.put(ORDER_BY, orderBy);
            return this;
        }

        @Override
        public Builder links(String links) {
            queryParams.put(LINKS, links);
            return this;
        }

        @Override
        public Builder q(String q) {
            queryParams.put(Q, q);
            return this;
        }

        @Override
        public Builder searchEngine(String searchEngine) {
            queryParams.put(SEARCH_ENGINE, searchEngine);
            return this;
        }

        @Override
        public Builder totalResults(boolean totalResults) {
            queryParams.put(TOTAL_RESULTS, String.valueOf(totalResults));
            return this;
        }

        @Override
        public Builder queryParams(MultiValuedMap<String, String> queryParams) {
            this.queryParams.putAll(queryParams);
            return this;
        }

        @Override
        public V1SearchQuery build() {
            return new DefaultV1SearchQuery(queryParams);
        }

    }

}
