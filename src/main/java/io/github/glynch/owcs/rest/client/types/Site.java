package io.github.glynch.owcs.rest.client.types;

import java.util.stream.Stream;

public enum Site implements Sites {
    AVISPORTS("avisports"), ADMIN_SITE("AdminSite"),;

    private final String name;

    private Site(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Site of(String name) {
        return Stream.of(values()).filter(site -> site.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
