package io.github.glynch.owcs.rest.client.authenticated.search;

public class DefaultCondition implements Condition {

    private final String field;
    private final Operation operation;
    private final String[] values;

    public DefaultCondition(String field, Operation operation, String... values) {
        this.field = field;
        this.operation = operation;
        this.values = values;
    }

    @Override
    public String field() {
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
        return "field:" + field + ":" + operation.toString();
    }

}
