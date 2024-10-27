package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Objects;

import io.github.glynch.owcs.rest.support.Field;
import io.github.glynch.owcs.rest.support.Fields;

public interface SortField {

    static SortField asc(Fields field) {
        return of(field, Order.ASC);
    }

    static SortField desc(Fields field) {
        return of(field, Order.DESC);
    }

    static SortField of(Fields field, Order order) {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(order, "order must not be null");
        return new DefaultSortField(field, order);
    }

    SortField NAME_ASC = asc(Field.NAME);
    SortField NAME_DESC = desc(Field.NAME);
    SortField UPDATEDDATE_DESC = desc(Field.UPDATEDDATE);
    SortField UPDATEDDATE_ASC = asc(Field.UPDATEDDATE);
    SortField CREATEDDATE_DESC = desc(Field.CREATEDDATE);
    SortField CREATEDDATE_ASC = asc(Field.CREATEDDATE);

    Fields field();

    Order order();

}
