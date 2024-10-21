package io.github.glynch.owcs.rest.client.support;

public enum Role implements Roles {

    ADVANCED_USER("AdvancedUser");

    private final String name;

    private Role(String name) {
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
