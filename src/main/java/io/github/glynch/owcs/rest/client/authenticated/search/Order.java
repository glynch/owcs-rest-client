package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

public enum Order {

    ASC("asc"),
    DESC("desc");

    private final String order;

    Order(String order) {
        this.order = order;
    }

    public String order() {
        return this.order;
    }

    public static Order of(String order) {
        return Arrays.stream(values()).filter(value -> value.order().equals(order)).findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return order;
    }

}
