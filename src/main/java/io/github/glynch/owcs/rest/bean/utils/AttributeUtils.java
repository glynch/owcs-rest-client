package io.github.glynch.owcs.rest.bean.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Attribute.Data;
import com.fatwire.rest.beans.Blob;
import com.fatwire.rest.beans.Struct;

public class AttributeUtils {

    private AttributeUtils() {
    }

    public static Attribute getAttribute(List<Attribute> attributes, String name) {
        Objects.requireNonNull(attributes, "attributes cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        return attributes.stream()
                .filter(attribute -> name.equals(attribute.getName()))
                .findFirst()
                .orElse(null);
    }

    public static Data getAttributeData(List<Attribute> attributes, String name) {
        Objects.requireNonNull(attributes, "attributes cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        Attribute attribute = getAttribute(attributes, name);
        return attribute != null ? attribute.getData() : null;
    }

    public static String asString(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getStringValue() : null;
    }

    public static Integer asInteger(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getIntegerValue() : null;
    }

    public static Boolean asBoolean(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.isBooleanValue() : null;
    }

    public static Blob asBlob(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getBlobValue() : null;
    }

    public static Long asLong(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getLongValue() : null;
    }

    public static Double asDouble(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getDoubleValue() : null;
    }

    public static BigDecimal asBigDecimal(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getDecimalValue() : null;
    }

    public static Struct asStruct(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getStructValue() : null;
    }

    public static AssetId asAssetId(List<Attribute> attributes, String name) {
        String value = asString(attributes, name);
        return value != null ? AssetIds.of(value) : null;
    }

}
