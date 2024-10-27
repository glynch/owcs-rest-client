package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.support.Fields;

public interface AssetQuery extends SearchQuery {

    String COUNT = "count";
    String START_INDEX = "startindex";
    String FIELDS = "fields";

    int count();

    int startIndex();

    SortField sortField();

    Fields[] fields();

}
