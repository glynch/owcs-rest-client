package io.github.glynch.owcs.rest.support.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fatwire.rest.beans.ViewTypeEnum;

public class ViewTypeEnumDeserializer extends StdDeserializer<ViewTypeEnum> {

    public ViewTypeEnumDeserializer(Class<?> clazz) {
        super(clazz);
    }

    public ViewTypeEnumDeserializer() {
        this(null);
    }

    @Override
    public ViewTypeEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        return ViewTypeEnum.fromValue(jsonParser.getText());
    }
}