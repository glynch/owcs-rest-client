package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

public enum SearchEngineType {

    DBBASIC("dbbasic");

    private final String type;

    SearchEngineType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    public String toString() {
        return type;
    }

    public static SearchEngineType of(String type) {
        return Arrays.stream(values()).filter(value -> value.type().equals(type)).findFirst()
                .orElse(null);
    }

}
