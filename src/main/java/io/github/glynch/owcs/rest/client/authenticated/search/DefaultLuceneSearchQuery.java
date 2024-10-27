package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Objects;

import org.apache.commons.collections4.MultiValuedMap;

import io.github.glynch.owcs.rest.support.Fields;

public class DefaultLuceneSearchQuery extends AbstractAssetQuery implements LuceneSearchQuery {

    private final String q;

    DefaultLuceneSearchQuery(int count, int startIndex, SortField sortField, String q, Fields... fields) {
        super(count, startIndex, sortField, fields);
        this.q = q;
    }

    @Override
    public String q() {
        return q;
    }

    @Override
    public MultiValuedMap<String, String> queryParams() {
        MultiValuedMap<String, String> queryParams = super.queryParams();
        if (q != null) {
            queryParams.put(Q, q);
        }

        return queryParams;
    }

    public static class DefaultLuceneSearchQueryBuilder implements Builder {

        private String q;
        private int startIndex = 0;
        private int count = -1;
        private SortField sortField;
        private Fields[] fields;

        @Override
        public Builder q(String q) {
            this.q = q;
            return this;
        }

        @Override
        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        @Override
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        @Override
        public Builder all() {
            return count(-1);
        }

        @Override
        public Builder sortField(SortField sortField) {
            Objects.requireNonNull(sortField, "sortField cannot be null");
            this.sortField = sortField;
            return this;
        }

        @Override
        public Builder fields(Fields... fields) {
            Objects.requireNonNull(fields, "fields cannot be null");
            this.fields = fields;
            return this;
        }

        @Override
        public LuceneSearchQuery build() {
            return new DefaultLuceneSearchQuery(count, startIndex, sortField, q, fields);
        }

    }

}
