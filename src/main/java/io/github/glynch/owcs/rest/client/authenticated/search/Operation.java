package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

public enum Operation {

    EQUALS("equals"),
    CONTAINS("contains"),
    RANGE("range"),
    RANGE_INCLUSIVE("range_inclusive"),
    STARTS_WITH("startswith"),
    PHRASE("phrase"),
    WILDCARD("wildcard"),
    SIMILAR("similar");

    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String operation() {
        return this.operation;
    }

    public static Operation of(String operation) {
        return Arrays.stream(values()).filter(value -> value.operation().equals(operation))
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return operation;
    }

}
