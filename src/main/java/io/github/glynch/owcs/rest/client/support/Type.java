package io.github.glynch.owcs.rest.client.support;

public enum Type implements TypeName {
    AVIARTICLE("AVIArticle");

    private final String name;

    private Type(String name) {
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
