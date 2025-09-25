package io.github.glynch.owcs.rest.client.authenticated.search;

public interface Condition {

    String field();

    Operation operation();

    String[] values();

    static Condition equals(String field, String value) {
        return new DefaultCondition(field, Operation.EQUALS, value);
    }

    static Condition contains(String field, String value) {
        return new DefaultCondition(field, Operation.CONTAINS, value);
    }

    static Condition range(String field, String from, String to) {
        return new DefaultCondition(field, Operation.RANGE, from, to);
    }

    static Condition wildcard(String field, String value) {
        return new DefaultCondition(field, Operation.WILDCARD, value);
    }

    static Condition similar(String field, String value) {
        return new DefaultCondition(field, Operation.SIMILAR, value);
    }

    static Condition phrase(String field, String value) {
        return new DefaultCondition(field, Operation.PHRASE, value);
    }

    static Condition startsWith(String field, String value) {
        return new DefaultCondition(field, Operation.STARTS_WITH, value);
    }

}
