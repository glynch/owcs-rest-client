package io.github.glynch.owcs.rest.client.v1.search;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public enum Link {
    NEXT("next"),
    PREV("prev"),
    FIRST("first"),
    LAST("last"),
    SELF("self"),
    CANONICAL("canonical"),
    DESCRIBED_BY("describedby");

    private final String value;

    Link(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Link of(String link) {
        return Arrays.stream(values()).filter(value -> StringUtils.equals(value.getValue(), link)).findFirst()
                .orElse(null);

    }

    @Override
    public String toString() {
        return value;
    }

}
