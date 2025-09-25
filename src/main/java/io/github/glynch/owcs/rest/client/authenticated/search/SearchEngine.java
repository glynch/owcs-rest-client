package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

public enum SearchEngine {

    DBBASIC("dbbasic"),
    LUCENE("lucene");

    private final String type;

    SearchEngine(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    public String toString() {
        return type;
    }

    public static SearchEngine of(String type) {
        return Arrays.stream(values()).filter(value -> value.type().equals(type)).findFirst()
                .orElse(null);
    }

}
