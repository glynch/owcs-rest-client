package io.github.glynch.owcs.rest.support;

import java.util.stream.Stream;

public enum User implements Users {
    FWADMIN("fwadmin");

    private final String name;

    User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static User of(String name) {
        return Stream.of(values())
                .filter(user -> user.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }
}
