package io.github.glynch.owcs.rest.bean.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.support.DefaultObjectMapper;

public class BeanUtils {

    private static final ObjectMapper objectMapper = new DefaultObjectMapper();

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
