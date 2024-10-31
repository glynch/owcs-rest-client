package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.types.Fields;

public class DefaultSortField implements SortField {

    private final Fields field;
    private final Order order;

    public DefaultSortField(Fields field, Order order) {
        this.field = field;
        this.order = order;
    }

    @Override
    public Fields field() {
        return field;
    }

    @Override
    public Order order() {
        return order;
    }

    @Override
    public String toString() {
        return "sortfield:" + field + ":" + order.toString();
    }

}
