package io.github.glynch.owcs.rest.client.support;

public enum Site implements Sites {
    AVISPORTS("avisports"), ADMIN_SITE("AdminSite");

    private final String name;

    private Site(String name) {
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
