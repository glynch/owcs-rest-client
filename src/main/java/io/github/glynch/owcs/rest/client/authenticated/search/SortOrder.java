package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

public enum SortOrder {

    ASC("asc"),
    DESC("desc");

    private final String order;

    SortOrder(String order) {
        this.order = order;
    }

    public String order() {
        return this.order;
    }

    public static SortOrder of(String order) {
        return Arrays.stream(values()).filter(value -> value.order().equals(order)).findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return order;
    }

}
