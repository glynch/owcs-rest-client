package io.github.glynch.owcs.rest.bean.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Attribute.Data;
import com.fatwire.rest.beans.Blob;
import com.fatwire.rest.beans.Struct;
import com.fatwire.rest.beans.Webreference;

public class AttributeUtils {

    private AttributeUtils() {
    }

    public static Attribute getAttribute(AssetBean assetBean, String name) {
        Assert.notNull(assetBean, "assetBean cannot be null");
        return assetBean.getAttributes().stream()
                .filter(attribute -> name.equals(attribute.getName()))
                .findFirst()
                .orElse(null);
    }

    public static Data getAttributeData(AssetBean assetBean, String name) {
        Assert.notNull(assetBean, "assetBean cannot be null");
        Assert.hasText(name, "name cannot be null");
        Attribute attribute = getAttribute(assetBean, name);
        return attribute != null ? attribute.getData() : null;
    }

    public static String asString(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getStringValue() : null;
    }

    public static Integer asInteger(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getIntegerValue() : null;
    }

    public static Boolean asBoolean(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.isBooleanValue() : null;
    }

    public static Blob asBlob(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getBlobValue() : null;
    }

    public static Long asLong(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getLongValue() : null;
    }

    public static Double asDouble(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getDoubleValue() : null;
    }

    public static BigDecimal asBigDecimal(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getDecimalValue() : null;
    }

    public static Struct asStruct(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getStructValue() : null;
    }

    public static Date asDate(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getDateValue() : null;
    }

    public static AssetId asAssetId(AssetBean assetBean, String name) {
        String value = asString(assetBean, name);
        return value != null ? AssetIds.of(value) : null;
    }

    public static List<String> asStringList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getStringLists() : new ArrayList<>();
    }

    public static List<Integer> asIntegerList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getIntegerLists() : new ArrayList<>();
    }

    public static List<Boolean> asBooleanList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getBooleanLists() : new ArrayList<>();
    }

    public static List<Blob> asBlobList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getBlobLists() : new ArrayList<>();
    }

    public static List<Long> asLongList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getLongLists() : Collections.emptyList();
    }

    public static List<Double> asDoubleList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getDoubleLists() : Collections.emptyList();
    }

    public static List<BigDecimal> asBigDecimalList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getDecimalLists() : Collections.emptyList();
    }

    public static List<Struct> asStructList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getStructLists() : Collections.emptyList();
    }

    public static List<AssetId> asAssetIdList(AssetBean assetBean, String name) {
        List<String> values = asStringList(assetBean, name);
        return values.stream()
                .map(AssetIds::of)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<Date> asDateList(AssetBean assetBean, String name) {
        Attribute.Data attributeData = getAttributeData(assetBean, name);
        return attributeData != null ? attributeData.getDateLists() : Collections.emptyList();
    }

    public static List<Webreference> getWebReferences(AssetBean assetBean) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, "Webreference");
        return data.getWebreferenceLists();
    }

    public static Attribute getOrCreateAttribute(AssetBean assetBean, String name) {
        Attribute attribute = getAttribute(assetBean, name);
        if (attribute == null) {
            attribute = new Attribute();
            attribute.setName(name);
            Data data = new Data();
            attribute.setData(data);
            assetBean.getAttributes().add(attribute);
        }
        return attribute;
    }

    public static Data getOrCreateAttributeData(AssetBean assetBean, String name) {
        Attribute attribute = getOrCreateAttribute(assetBean, name);
        Data data = attribute.getData();
        if (data == null) {
            data = new Data();
            attribute.setData(data);
        }
        return data;
    }

}
