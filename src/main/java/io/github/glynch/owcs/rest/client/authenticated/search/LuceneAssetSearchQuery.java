package io.github.glynch.owcs.rest.client.authenticated.search;

public interface LuceneAssetSearchQuery extends AssetSearchQuery {

    String q();

    default SearchEngine searchEngine() {
        return SearchEngine.LUCENE;
    }

}
