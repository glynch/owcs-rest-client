package io.github.glynch.owcs.rest.support;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
    private static final ObjectMapper INSTANCE = new DefaultObjectMapper();

    private ObjectMapperFactory() {
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }

}
