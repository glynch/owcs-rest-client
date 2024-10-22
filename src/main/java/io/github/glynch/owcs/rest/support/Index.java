package io.github.glynch.owcs.rest.support;

import java.util.stream.Stream;

public enum Index implements Indexes {
    GLOBAL("Global"),
    AVIARTICLE("AVIArticle"),
    PAGE("Page"),;

    private final String name;

    private Index(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Index of(String name) {
        return Stream.of(values()).filter(site -> site.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
