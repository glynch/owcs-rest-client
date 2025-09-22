package io.github.glynch.owcs.rest.bean.builders;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AttributeDefBean;
import com.fatwire.rest.beans.AttributeTypeEnum;
import com.fatwire.rest.beans.ValueCountEnum;

public class AssetTypeBeanBuilder {

    private final AssetTypeBean assetTypeBean;

    AssetTypeBeanBuilder(String name, String subtype) {
        this.assetTypeBean = new AssetTypeBean();
        this.assetTypeBean.setName(name);
        this.assetTypeBean.setSubtype(subtype);
        this.assetTypeBean.setProxyAsset(false);
        this.assetTypeBean.setCanBeChild(true);
    }

    public AssetTypeBeanBuilder description(String description) {
        assetTypeBean.setDescription(description);
        return this;
    }

    public AssetTypeBeanBuilder proxyAsset() {
        assetTypeBean.setProxyAsset(true);
        return this;
    }

    public AssetTypeBeanBuilder canBeChild(boolean canBeChild) {
        assetTypeBean.setCanBeChild(canBeChild);
        return this;
    }

    public AssetTypeBeanBuilder attribute(AttributeDefBean attributeDefBean) {
        Assert.notNull(attributeDefBean, "attributeDefBean cannot be null");
        Assert.hasText(attributeDefBean.getName(), "Attribute name cannot be empty or null");
        Assert.notNull(attributeDefBean.getType(), "Attribute type cannot be null");
        Assert.notNull(attributeDefBean.getIsMultiple(), "Attribute valueCount cannot be null");
        assetTypeBean.getAttributes().add(attributeDefBean);
        return this;
    }

    public AssetTypeBeanBuilder attribute(String name, String description, AttributeTypeEnum type, boolean isRequired,
            int dataLength, ValueCountEnum valueCount) {
        AttributeDefBean attributeDefBean = new AttributeDefBean();
        attributeDefBean.setName(name);
        attributeDefBean.setDescription(description);
        attributeDefBean.setType(type);
        attributeDefBean.setIsDataMandatory(isRequired);
        attributeDefBean.setIsMetaData(false);
        attributeDefBean.setIsMultiple(valueCount);
        attributeDefBean.setDataLength(dataLength);
        return attribute(attributeDefBean);
    }

    public AssetTypeBeanBuilder singleValuedAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        return attribute(name, description, type, isRequired, dataLength, ValueCountEnum.SINGLE);
    }

    public AssetTypeBeanBuilder multiValuedAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        return attribute(name, description, type, isRequired, dataLength, ValueCountEnum.MULTI);
    }

    public AssetTypeBeanBuilder singleValuedUniqueAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        return attribute(name, description, type, isRequired, dataLength, ValueCountEnum.SINGLEUNIQUE);
    }

    public AssetTypeBeanBuilder multiValuedOrderedAttribute(String name, String description, AttributeTypeEnum type,
            boolean isRequired, int dataLength) {
        return attribute(name, description, type, isRequired, dataLength, ValueCountEnum.MULTI_ORDERED);
    }

    public AssetTypeBean build() {
        if (assetTypeBean.isProxyAsset() && assetTypeBean.getAttributes().size() > 0) {
            throw new IllegalStateException("Proxy asset type cannot define custom attributes");
        }
        return assetTypeBean;
    }

}
