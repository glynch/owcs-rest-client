package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.authenticated.search.DefaultLuceneSearchQuery.DefaultLuceneSearchQueryBuilder;
import io.github.glynch.owcs.rest.client.types.Fields;

public interface LuceneSearchQuery extends AssetQuery {

    String Q = "q";

    String q();

    static Builder builder() {
        return new DefaultLuceneSearchQueryBuilder();
    }

    interface Builder {

        Builder q(String q);

        Builder startIndex(int startIndex);

        Builder count(int count);

        Builder all();

        Builder sortField(SortField sortField);

        Builder fields(Fields... fields);

        LuceneSearchQuery build();
    }
}
