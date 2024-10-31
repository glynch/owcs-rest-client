package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Objects;

import io.github.glynch.owcs.rest.client.types.Fields;

public class DefaultCondition implements Condition {

    private final Fields field;
    private final Operation operation;
    private final String[] values;

    DefaultCondition(Fields field, Operation operation, String... values) {
        Objects.requireNonNull(field, "field is required");
        Objects.requireNonNull(operation, "operation is required");
        Objects.requireNonNull(values, "values is required");
        this.field = field;
        this.operation = operation;
        this.values = values;
    }

    @Override
    public Fields field() {
        return field;
    }

    @Override
    public Operation operation() {
        return operation;
    }

    @Override
    public String[] values() {
        return values;
    }

    @Override
    public String toString() {
        return "field:" + field.toString() + ":" + operation.toString();
    }

}
