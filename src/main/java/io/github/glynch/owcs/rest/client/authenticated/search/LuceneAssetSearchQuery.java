package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.authenticated.search.DefaultLuceneAssetSearchQuery.LuceneAssetSearchQueryBuilder;

public interface LuceneAssetSearchQuery extends AssetSearchQuery {

    String q();

    default SearchEngine searchEngine() {
        return SearchEngine.LUCENE;
    }

    interface Builder {

        Builder q(String q);

        Builder sortField(SortField sortField);

        Builder field(String field);

        Builder count(int count);

        Builder startIndex(int startIndex);

        LuceneAssetSearchQuery build();

    }

    public static Builder builder() {
        return new LuceneAssetSearchQueryBuilder();
    }

}
