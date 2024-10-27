package io.github.glynch.owcs.rest.support;

import java.util.stream.Stream;

public enum Field implements Fields {

    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    UPDATEDDATE("updateddate"),
    CREATEDDATE("createddate"),
    UPDATEDBY("updatedby"),
    CREATEDBY("createdby"),;

    private final String name;

    private Field(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Field of(String name) {
        return Stream.of(values()).filter(site -> site.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
