package io.github.glynch.owcs.rest.client.authenticated.search;

public interface DBBasicAssetSearchQuery extends AssetSearchQuery {

    Condition[] conditions();

    default SearchEngine searchEngine() {
        return SearchEngine.DBBASIC;
    }

}
