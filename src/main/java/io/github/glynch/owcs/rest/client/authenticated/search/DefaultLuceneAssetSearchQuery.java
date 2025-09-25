package io.github.glynch.owcs.rest.client.authenticated.search;

import org.springframework.util.MultiValueMap;

public class DefaultLuceneAssetSearchQuery extends AbstractAssetSearchQuery implements LuceneAssetSearchQuery {

    private final String q;

    public DefaultLuceneAssetSearchQuery(String q, SortField sortField, int count, int startIndex, String[] fields) {
        super(sortField, count, startIndex, fields);
        this.q = q;
    }

    @Override
    public MultiValueMap<String, String> queryParams() {
        MultiValueMap<String, String> queryParams = super.queryParams();
        if (q != null) {
            queryParams.add("q", q);
        }

        return queryParams;
    }

    @Override
    public String q() {
        return q;
    }

}
