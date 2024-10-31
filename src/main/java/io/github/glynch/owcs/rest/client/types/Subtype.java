package io.github.glynch.owcs.rest.client.types;

public enum Subtype implements Subtypes {

    Article("Article");

    private final String name;

    private Subtype(String name) {
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
