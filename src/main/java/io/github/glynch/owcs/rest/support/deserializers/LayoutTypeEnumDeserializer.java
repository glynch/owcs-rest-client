package io.github.glynch.owcs.rest.support.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fatwire.rest.beans.LayoutTypeEnum;

public class LayoutTypeEnumDeserializer extends StdDeserializer<LayoutTypeEnum> {

    public LayoutTypeEnumDeserializer(Class<?> clazz) {
        super(clazz);
    }

    public LayoutTypeEnumDeserializer() {
        this(null);
    }

    @Override
    public LayoutTypeEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        return LayoutTypeEnum.fromValue(jsonParser.getText());
    }
}