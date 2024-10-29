package io.github.glynch.owcs.rest.support;

import java.util.stream.Stream;

public enum Type implements Types {
    AVIARTICLE("AVIArticle"),
    AVIIMAGE("AVIImage"),
    ARTICLECATEGORY("ArticleCategory"),
    CONTENTQUERY("ContentQuery"),
    RECOMMENDATION("AdvCols"),
    PAGE("Page"),
    CSELEMENT("CSElement"),
    SITEENTRY("SiteEntry"),
    TEMPLATE("Template"),
    DEVICE("Device"),
    DIMENSION("Dimension"),
    DIMENSION_SET("DimensionSet"),
    APPLICATION("FW_Application"),
    VIEW("FW_View"),
    PAGE_ATTRIBUTE("PageAttribute"),
    PAGE_DEFINITION("PageDefinition"),
    PAGE_FILTER("PageFilter"),
    SLOTS("Slots"),
    CONTROLLER("WCS_Controller"),
    WEBROOT("WebRoot"),
    SITE_NAVIGATION("SiteNavigation"),
    SEGMENTS("Segments"),;

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
