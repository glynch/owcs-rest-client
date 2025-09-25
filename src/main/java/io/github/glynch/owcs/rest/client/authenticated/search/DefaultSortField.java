package io.github.glynch.owcs.rest.client.authenticated.search;

public class DefaultSortField implements SortField {

    private final String field;
    private final SortOrder sortOrder;

    public DefaultSortField(String field, SortOrder sortOrder) {
        this.field = field;
        this.sortOrder = sortOrder;
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public SortOrder sortOrder() {
        return sortOrder;
    }

    @Override
    public String toString() {
        return "sortfield:" + field + ":" + sortOrder.toString();
    }

}
