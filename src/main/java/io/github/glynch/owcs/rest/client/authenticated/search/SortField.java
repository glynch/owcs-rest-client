package io.github.glynch.owcs.rest.client.authenticated.search;

import org.springframework.util.Assert;

public interface SortField {

    static SortField asc(String field) {
        return of(field, SortOrder.ASC);
    }

    static SortField desc(String field) {
        return of(field, SortOrder.DESC);
    }

    static SortField of(String field, SortOrder sortOrder) {
        Assert.hasText(field, "field must not be empty or null");
        Assert.notNull(sortOrder, "sortOrder must not be null");
        return new DefaultSortField(field, sortOrder);
    }

    SortField NAME_ASC = asc("name");
    SortField NAME_DESC = desc("name");
    SortField UPDATEDDATE_DESC = desc("updateddate");
    SortField UPDATEDDATE_ASC = asc("updateddate");
    SortField CREATEDDATE_DESC = desc("createddate");
    SortField CREATEDDATE_ASC = asc("createddate");

    String field();

    SortOrder sortOrder();

}
