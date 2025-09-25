package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.authenticated.search.DefaultDBBasicAssetSearchQuery.DBBasicAssetSearchQueryBuilder;

public interface DBBasicAssetSearchQuery extends AssetSearchQuery {

    Condition[] conditions();

    default SearchEngine searchEngine() {
        return SearchEngine.DBBASIC;
    }

    interface Builder {
        Builder sortField(SortField sortField);

        Builder field(String field);

        Builder count(int count);

        Builder startIndex(int startIndex);

        Builder condition(Condition condition);

        DBBasicAssetSearchQuery build();
    }

    public static Builder builder() {
        return new DBBasicAssetSearchQueryBuilder();
    }

}
