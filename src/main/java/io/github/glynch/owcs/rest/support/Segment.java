package io.github.glynch.owcs.rest.support;

import java.util.Arrays;

public enum Segment implements Segments {
    FEMALE("Female"),
    MALE("Male"),
    TENNIS_FAN("Tennis Fan"),
    SURFING_FAN("Surfing Fan"),
    SKIING_FAN("Skiing Fan"),
    BASEBALL_FAN("Baseball Fan"),
    RUNNING_FAN("Running Fan"),;

    private final String name;

    private Segment(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Segment of(String name) {
        return Arrays.stream(values())
                .filter(segment -> segment.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

}
