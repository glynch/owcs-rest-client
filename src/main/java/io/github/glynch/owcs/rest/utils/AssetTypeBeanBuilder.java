package io.github.glynch.owcs.rest.utils;

import java.util.Objects;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AttributeDefBean;
import com.fatwire.rest.beans.AttributeTypeEnum;
import com.fatwire.rest.beans.ValueCountEnum;

public class AssetTypeBeanBuilder {

    private final AssetTypeBean assetTypeBean;

    public static AssetTypeBeanBuilder builder(String name, String subtype) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(subtype, "subtype cannot be null");
        return new AssetTypeBeanBuilder(name, subtype);
    }

    private AssetTypeBeanBuilder(String name, String subtype) {
        this.assetTypeBean = new AssetTypeBean();
        this.assetTypeBean.setName(name);
        this.assetTypeBean.setSubtype(subtype);
        this.assetTypeBean.setProxyAsset(false);
        this.assetTypeBean.setCanBeChild(true);
    }

    public AssetTypeBeanBuilder description(String description) {
        Objects.requireNonNull(description, "description cannot be null");
        assetTypeBean.setDescription(description);
        return this;
    }

    public AssetTypeBeanBuilder isProxyAsset() {
        assetTypeBean.setProxyAsset(true);
        return this;
    }

    public AssetTypeBeanBuilder canBeChild(boolean canBeChild) {
        assetTypeBean.setCanBeChild(canBeChild);
        return this;

    }

    public AssetTypeBeanBuilder attribute(String name, String description, AttributeTypeEnum type, boolean isMandatory,
            int dataLength, ValueCountEnum valueCount) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(description, "description cannot be null");
        Objects.requireNonNull(type, "type cannot be null");
        Objects.requireNonNull(valueCount, "valueCount cannot be null");
        AttributeDefBean attributeDefBean = new AttributeDefBean();
        attributeDefBean.setName(name);
        attributeDefBean.setDescription(description);
        attributeDefBean.setType(type);
        attributeDefBean.setIsDataMandatory(isMandatory);
        attributeDefBean.setIsMetaData(false);
        attributeDefBean.setIsMultiple(valueCount);
        attributeDefBean.setDataLength(dataLength);
        assetTypeBean.getAttributes().add(attributeDefBean);
        return this;
    }

    public AssetTypeBeanBuilder attribute(AttributeDefBean attributeDefBean) {
        Objects.requireNonNull(attributeDefBean, "attributeDefBean cannot be null");
        assetTypeBean.getAttributes().add(attributeDefBean);
        return this;
    }

    public AssetTypeBeanBuilder singleValuedAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(description, "description cannot be null");
        Objects.requireNonNull(type, "type cannot be null");
        AttributeDefBean attributeDefBean = new AttributeDefBean();
        attributeDefBean.setName(name);
        attributeDefBean.setDescription(description);
        attributeDefBean.setType(type);
        attributeDefBean.setIsDataMandatory(isRequired);
        attributeDefBean.setIsMetaData(false);
        attributeDefBean.setIsMultiple(ValueCountEnum.SINGLE);
        attributeDefBean.setDataLength(dataLength);
        return attribute(attributeDefBean);
    }

    public AssetTypeBeanBuilder multiValuedAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(description, "description cannot be null");
        Objects.requireNonNull(type, "type cannot be null");
        AttributeDefBean attributeDefBean = new AttributeDefBean();
        attributeDefBean.setName(name);
        attributeDefBean.setDescription(description);
        attributeDefBean.setType(type);
        attributeDefBean.setIsDataMandatory(isRequired);
        attributeDefBean.setIsMetaData(false);
        attributeDefBean.setIsMultiple(ValueCountEnum.MULTI);
        attributeDefBean.setDataLength(dataLength);
        return attribute(attributeDefBean);
    }

    public AssetTypeBeanBuilder singleValuedUniqueAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(description, "description cannot be null");
        Objects.requireNonNull(type, "type cannot be null");
        AttributeDefBean attributeDefBean = new AttributeDefBean();
        attributeDefBean.setName(name);
        attributeDefBean.setDescription(description);
        attributeDefBean.setType(type);
        attributeDefBean.setIsDataMandatory(isRequired);
        attributeDefBean.setIsMetaData(false);
        attributeDefBean.setIsMultiple(ValueCountEnum.SINGLEUNIQUE);
        attributeDefBean.setDataLength(dataLength);
        return attribute(attributeDefBean);
    }

    public AssetTypeBeanBuilder multiValuedOrderedAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(description, "description cannot be null");
        Objects.requireNonNull(type, "type cannot be null");
        AttributeDefBean attributeDefBean = new AttributeDefBean();
        attributeDefBean.setName(name);
        attributeDefBean.setDescription(description);
        attributeDefBean.setType(type);
        attributeDefBean.setIsDataMandatory(isRequired);
        attributeDefBean.setIsMetaData(false);
        attributeDefBean.setIsMultiple(ValueCountEnum.MULTI_ORDERED);
        attributeDefBean.setDataLength(dataLength);
        return attribute(attributeDefBean);
    }

    public AssetTypeBean build() {
        return assetTypeBean;
    }

}
