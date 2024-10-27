package io.github.glynch.owcs.rest.support;

import java.util.Arrays;

public enum Association implements Associations {

    CONTENT_LIST1("contentList1"),;

    private String name;

    private Association(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Association of(String name) {
        return Arrays.stream(values()).filter(association -> association.getName().equals(name)).findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
