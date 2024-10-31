package io.github.glynch.owcs.rest.client.bean.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.ViewTypeEnum;

import io.github.glynch.owcs.rest.client.deserializers.LayoutTypeEnumDeserializer;
import io.github.glynch.owcs.rest.client.deserializers.ViewTypeEnumDeserializer;

public abstract class BeanUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();
    private static final SimpleModule simpleModule = new SimpleModule();

    static {

        simpleModule.addDeserializer(LayoutTypeEnum.class, new LayoutTypeEnumDeserializer());
        simpleModule.addDeserializer(ViewTypeEnum.class, new ViewTypeEnumDeserializer());
        objectMapper.registerModule(javaTimeModule);
        objectMapper.registerModule(simpleModule);
        objectMapper.registerModule(new JaxbAnnotationModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

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
