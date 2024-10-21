package io.github.glynch.owcs.rest.client.support;

public enum Index implements IndexName {
    GLOBAL("Global");

    private final String name;

    private Index(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
