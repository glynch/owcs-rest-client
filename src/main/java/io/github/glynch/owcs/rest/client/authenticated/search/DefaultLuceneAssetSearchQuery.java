package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

public class DefaultLuceneAssetSearchQuery extends AbstractAssetSearchQuery implements LuceneAssetSearchQuery {

    private static final String Q = "q";
    private final String q;

    public DefaultLuceneAssetSearchQuery(String q, SortField sortField, int count, int startIndex, String[] fields) {
        super(sortField, count, startIndex, fields);
        this.q = q;
    }

    @Override
    public MultiValueMap<String, String> queryParams() {
        MultiValueMap<String, String> queryParams = super.queryParams();
        if (q != null) {
            queryParams.add(Q, q);
        }

        return queryParams;
    }

    @Override
    public String q() {
        return q;
    }

    public static class LuceneAssetSearchQueryBuilder implements Builder {

        private String q;
        private SortField sortField;
        private int count = Integer.MAX_VALUE;
        private int startIndex = 0;
        private List<String> fields = new ArrayList<>();

        @Override
        public Builder q(String q) {
            Assert.hasText(q, "q cannot be empty or null");
            this.q = q;
            return this;
        }

        @Override
        public Builder sortField(SortField sortField) {
            Assert.notNull(sortField, "sortField cannot be null");
            this.sortField = sortField;
            return this;
        }

        @Override
        public Builder field(String field) {
            Assert.hasText(field, "field cannot be empty or null");
            fields.add(field);

            return this;
        }

        @Override
        public Builder count(int count) {
            Assert.isTrue(count > 0, "count must be > 0");
            this.count = count;
            return this;
        }

        @Override
        public Builder startIndex(int startIndex) {
            Assert.isTrue(startIndex >= 0, "startIndex must be >= 0");
            this.startIndex = startIndex;
            return this;
        }

        @Override
        public LuceneAssetSearchQuery build() {
            return new DefaultLuceneAssetSearchQuery(q, sortField, count, startIndex, fields.toArray(new String[0]));
        }

    }

}
