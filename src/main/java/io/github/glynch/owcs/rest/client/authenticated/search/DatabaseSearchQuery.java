package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.authenticated.search.DefaultDatabaseSearchQuery.DefaultDatabaseSearchQueryBuilder;
import io.github.glynch.owcs.rest.client.types.Fields;

public interface DatabaseSearchQuery extends AssetQuery {

    Condition[] conditions();

    static DatabaseSearchQuery.Builder builder() {
        return new DefaultDatabaseSearchQueryBuilder();
    }

    interface Builder {

        Builder conditions(Condition... conditions);

        Builder startIndex(int startIndex);

        Builder count(int count);

        Builder all();

        Builder sortField(SortField sortField);

        Builder fields(Fields... fields);

        DatabaseSearchQuery build();
    }

}
