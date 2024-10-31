package io.github.glynch.owcs.rest.client.types;

import java.util.stream.Stream;

public enum Version implements Versions {
    V1("v1");

    private final String name;

    Version(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Version of(String name) {
        return Stream.of(values()).filter(site -> site.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}