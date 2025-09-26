package io.github.glynch.owcs.rest.client.authenticated.search;

public interface AssetSearchQuery extends SearchQuery {

    SortField sortField();

    String[] fields();

    int count();

    int startIndex();

    SearchEngine searchEngine();

}
