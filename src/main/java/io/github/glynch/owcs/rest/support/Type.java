package io.github.glynch.owcs.rest.support;

import java.util.stream.Stream;

public enum Type implements Types {
    AVIARTICLE("AVIArticle"),
    AVIIMAGE("AVIImage"),
    ARTICLECATEGORY("ArticleCategory"),
    CONTENTQUERY("ContentQuery"),
    ADVCOLS("AdvCols"),
    PAGE("Page"),;

    private final String name;

    private Type(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Type of(String name) {
        return Stream.of(values()).filter(site -> site.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
