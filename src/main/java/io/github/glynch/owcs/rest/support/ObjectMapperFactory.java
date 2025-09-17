package io.github.glynch.owcs.rest.support;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class ObjectMapperFactory {

    private static final ObjectMapper INSTANCE = new DefaultObjectMapper();

    /**
     * Returns a singleton instance of the ObjectMapper.
     *
     * @return the singleton ObjectMapper instance
     */
    public static ObjectMapper getInstance() {
        return INSTANCE;
    }

}
