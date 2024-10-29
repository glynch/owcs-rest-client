package io.github.glynch.owcs.rest.support;

import java.util.stream.Stream;

public enum Role implements Roles {

    ADVANCED_USER("AdvancedUser"),
    GENERAL_ADMIN("GeneralAdmin"),
    SITE_ADMIN("SiteAdmin"),;

    private final String name;

    private Role(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Role of(String name) {
        return Stream.of(values()).filter(site -> site.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
