package io.github.glynch.owcs.rest.client.bean.utils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Blob;
import com.fatwire.rest.beans.Struct;

public abstract class AttributeUtils {

    private AttributeUtils() {
    }

    public static Attribute getAttribute(List<Attribute> attributes, String name) {
        Objects.requireNonNull(attributes, "attributes cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        return attributes.stream()
                .filter(a -> name.equals(a.getName()))
                .findFirst()
                .orElse(null);
    }

    public static Attribute.Data getAttributeData(List<Attribute> attributes, String name) {
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

    public static List<String> asStringList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getStringLists() : Collections.emptyList();
    }

    public static List<Integer> asIntegerList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getIntegerLists() : Collections.emptyList();
    }

    public static List<Boolean> asBooleanList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getBooleanLists() : Collections.emptyList();
    }

    public static List<Blob> asBlobList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getBlobLists() : Collections.emptyList();
    }

    public static Date asDate(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getDateValue() : null;
    }

    public static List<Long> asLongList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getLongLists() : Collections.emptyList();
    }

    public static List<Double> asDoubleList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getDoubleLists() : Collections.emptyList();
    }

    public static List<BigDecimal> asBigDecimalList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getDecimalLists() : Collections.emptyList();
    }

    public static List<Struct> asStructList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getStructLists() : Collections.emptyList();
    }

    public static List<AssetId> asAssetIdList(List<Attribute> attributes, String name) {
        List<String> values = asStringList(attributes, name);
        return values.stream()
                .map(AssetIds::of)
                .filter(Objects::nonNull)
                .toList();
    }

    public static List<?> asDateList(List<Attribute> attributes, String name) {
        Attribute.Data attributeData = getAttributeData(attributes, name);
        return attributeData != null ? attributeData.getDateLists() : Collections.emptyList();
    }

    public static Attribute getOrCreateAttribute(List<Attribute> attributes, String name) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute attribute = getAttribute(attributes, name);
        if (attribute == null) {
            attribute = new Attribute();
            attribute.setName(name);
            attributes.add(attribute);
        }
        return attribute;
    }

    public static Attribute.Data getOrCreateAttributeData(List<Attribute> attributes, String name) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute attribute = getOrCreateAttribute(attributes, name);
        Attribute.Data data = attribute.getData();
        if (data == null) {
            data = new Attribute.Data();
            attribute.setData(data);
        }
        return data;
    }

}
