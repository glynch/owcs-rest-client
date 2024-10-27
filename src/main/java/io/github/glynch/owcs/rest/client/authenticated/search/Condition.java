package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.support.Fields;

public interface Condition {

    Fields field();

    Operation operation();

    String[] values();

    static Condition equals(Fields field, String value) {
        return new DefaultCondition(field, Operation.EQUALS, value);
    }

    static Condition contains(Fields field, String value) {
        return new DefaultCondition(field, Operation.CONTAINS, value);
    }

    static Condition range(Fields field, String from, String to) {
        return new DefaultCondition(field, Operation.RANGE, from, to);
    }

    static Condition wildcard(Fields field, String value) {
        return new DefaultCondition(field, Operation.WILDCARD, value);
    }

    static Condition similar(Fields field, String value) {
        return new DefaultCondition(field, Operation.SIMILAR, value);
    }

    static Condition phrase(Fields field, String value) {
        return new DefaultCondition(field, Operation.PHRASE, value);
    }

    static Condition startsWith(Fields field, String value) {
        return new DefaultCondition(field, Operation.STARTS_WITH, value);
    }

}
