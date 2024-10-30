package io.github.glynch.owcs.rest.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.ViewTypeEnum;

import io.github.glynch.owcs.rest.support.LayoutTypeEnumDeserializer;
import io.github.glynch.owcs.rest.support.ViewTypeEnumDeserializer;

public abstract class AssetBeanUtils {

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

    private AssetBeanUtils() {
    }

    public static String toString(AssetBean assetBean) {
        try {
            return objectMapper.writeValueAsString(assetBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
