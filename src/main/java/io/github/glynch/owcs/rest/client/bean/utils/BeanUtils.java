package io.github.glynch.owcs.rest.client.bean.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.support.ObjectMapperFactory;

public abstract class BeanUtils {

    private static ObjectMapper objectMapper = ObjectMapperFactory.getInstance();

    private BeanUtils() {
    }

    public static String toString(Object bean) {
        try {
            return objectMapper.writeValueAsString(bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
