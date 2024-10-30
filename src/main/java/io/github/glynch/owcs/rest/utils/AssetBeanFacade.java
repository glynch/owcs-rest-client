package io.github.glynch.owcs.rest.utils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Associations;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Blob;
import com.fatwire.rest.beans.Parent;
import com.fatwire.rest.beans.Struct;

public class AssetBeanFacade {

    private static String TEMPLATE = "template";
    private static String FW_UID = "fw_uid";
    private static String CREATEDD_DATE = "createddate";
    private static String UPDATED_DATE = "updateddate";
    private static String START_DATE = "startdate";
    private static String END_DATE = "enddate";
    private static String CREATED_BY = "createdby";
    private static String UPDATED_BY = "updatedby";
    private static String FLEX_TEMPLATE_ID = "flextemplateid";
    private static String FLEX_GROUP_TEMPLATEID = "flexgrouptemplateid";
    private static String FW_TAGS = "fw_tags";

    private final AssetBean assetBean;
    private final List<Attribute> attributes;
    private final List<Parent> parents;
    private final List<Association> associations;

    AssetBeanFacade(AssetBean assetBean) {
        this.assetBean = assetBean;
        this.attributes = assetBean.getAttributes();
        this.parents = assetBean.getParents();
        if (assetBean.getAssociations() == null) {
            assetBean.setAssociations(new Associations());
        }
        this.associations = assetBean.getAssociations().getAssociations();
    }

    AssetBeanFacade() {
        this(new AssetBean());
    }

    public String getId() {
        return assetBean.getId();
    }

    public AssetId getAssetId() {
        return AssetIds.of(assetBean.getId());
    }

    public void setAssetId(AssetId assetId) {
        Objects.requireNonNull(assetId, "assetId cannot be null");
        assetBean.setId(assetId.toString());
    }

    public String getName() {
        return assetBean.getName();
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "name cannot be null");
        assetBean.setName(name);
    }

    public String getFwUid() {
        return AttributeUtils.asString(attributes, FW_UID);
    }

    public AssetId getFlexTemplateId() {
        return AttributeUtils.asAssetId(attributes, FLEX_TEMPLATE_ID);
    }

    public AssetId getFlexGroupTemplateId() {
        return AttributeUtils.asAssetId(attributes, FLEX_GROUP_TEMPLATEID);
    }

    public String getDescription() {
        return assetBean.getDescription();
    }

    public void setDescription(String description) {
        Objects.requireNonNull(description, "description cannot be null");
        assetBean.setDescription(description);
    }

    public String getTemplate() {
        return AttributeUtils.asString(attributes, TEMPLATE);
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(AttributeUtils.asStringList(attributes, FW_TAGS));
    }

    public void setTags(String... tags) {
        Objects.requireNonNull(tags, "tags cannot be null");
        List<String> tagList = AttributeUtils.asStringList(attributes, FW_TAGS);
        tagList.clear();
        tagList.addAll(List.of(tags));
    }

    public void addTags(String... tags) {
        Objects.requireNonNull(tags, "tags cannot be null");
        List<String> tagList = AttributeUtils.asStringList(attributes, FW_TAGS);
        for (String tag : tags) {
            if (!tagList.contains(tag)) {
                tagList.add(tag);
            }
        }
    }

    public void setTemplate(String template) {
        Objects.requireNonNull(template, "template cannot be null");
        setStringAttribute(TEMPLATE, template);
    }

    public String getSubtype() {
        return assetBean.getSubtype();
    }

    public void setSubtype(String subtype) {
        assetBean.setSubtype(subtype);
    }

    public String getStatus() {
        return assetBean.getStatus();
    }

    public Date getCreatedDate() {
        return AttributeUtils.asDate(attributes, CREATEDD_DATE);
    }

    public Date getUpdatedDate() {
        return AttributeUtils.asDate(attributes, UPDATED_DATE);
    }

    public String getCreatedBy() {
        return AttributeUtils.asString(attributes, CREATED_BY);
    }

    public String getUpdatedBy() {
        return AttributeUtils.asString(attributes, UPDATED_BY);
    }

    public Date getStartDate() {
        return AttributeUtils.asDate(attributes, START_DATE);
    }

    public void setStartDate(Date startDate) {
        Objects.requireNonNull(startDate, "startDate cannot be null");
        setDateAttribute(START_DATE, startDate);
    }

    public void setEndDate(Date endDate) {
        Objects.requireNonNull(endDate, "endDate cannot be null");
        setDateAttribute(END_DATE, endDate);
    }

    public Date getEndDate() {
        return AttributeUtils.asDate(attributes, END_DATE);
    }

    public List<String> getSites() {
        return Collections.unmodifiableList(assetBean.getPublists());
    }

    public void setSites(String... sites) {
        Objects.requireNonNull(sites, "sites cannot be null");
        assetBean.getPublists().clear();
        assetBean.getPublists().addAll(List.of(sites));
    }

    public void addSites(String... sites) {
        Objects.requireNonNull(sites, "sites cannot be null");
        List<String> publists = assetBean.getPublists();
        for (String site : sites) {
            if (!publists.contains(site)) {
                publists.add(site);
            }
        }

    }

    public void setAttribute(String name, Object value) {
        System.out.println(value.getClass().getName());
        Objects.requireNonNull(name, "name cannot be null");
        if (value != null) {
            if (value instanceof List<?>) {
                setMultiValuedAttribute(name, (List<?>) value);
            } else {
                setSingleValuedAttribute(name, value);
            }
        }
    }

    private void setSingleValuedAttribute(String name, Object value) {
        if (value instanceof String) {
            setStringAttribute(name, (String) value);
        } else if (value instanceof AssetId) {
            setStringAttribute(name, value.toString());
        } else if (value instanceof Date) {
            setDateAttribute(name, (Date) value);
        } else if (value instanceof Boolean) {
            setBooleanAttribute(name, (Boolean) value);
        } else if (value instanceof Double) {
            setDoubleAttribute(name, (Double) value);
        } else if (value instanceof BigDecimal) {
            setBigDecimalAttribute(name, (BigDecimal) value);
        } else if (value instanceof Integer) {
            setIntegerAttribute(name, (Integer) value);
        } else if (value instanceof Long) {
            setLongAttribute(name, (Long) value);
        } else if (value instanceof Struct) {
            setStructAttribute(name, (Struct) value);
        } else if (value instanceof Blob) {
            setBlobAttribute(name, (Blob) value);
        } else {
            throw new IllegalArgumentException("Unsupported attribute type: " + value.getClass().getName());
        }
    }

    @SuppressWarnings("unchecked")
    private void setMultiValuedAttribute(String name, List<?> value) {
        if (CollectionUtils.isNotEmpty(value)) {
            Object firstValue = value.get(0);
            if (firstValue instanceof String) {
                setMultiValuedStringAttribute(name, (List<String>) value);
            } else if (firstValue instanceof AssetId) {
                setMultiValuedAssetIdAttribute(name, (List<AssetId>) value);
            } else if (firstValue instanceof Date) {
                setMultiValuedDateAttribute(name, (List<Date>) value);
            } else if (firstValue instanceof Boolean) {
                setMultiValuedBooleanAttribute(name, (List<Boolean>) value);
            } else if (firstValue instanceof Double) {
                setMutliValuedDoubleAttribute(name, (List<Double>) value);
            } else if (firstValue instanceof BigDecimal) {
                setMultiValuedBigDecimalAttribute(name, (List<BigDecimal>) value);
            } else if (firstValue instanceof Integer) {
                setMultiValuedIntegerAttribute(name, (List<Integer>) value);
            } else if (firstValue instanceof Long) {
                setMultiValuedLongAttribute(name, (List<Long>) value);
            } else if (firstValue instanceof Struct) {
                setMultiValuedStructAttribute(name, (List<Struct>) value);
            } else if (firstValue instanceof Blob) {
                setMultiValuedBlobAttribute(name, (List<Blob>) value);
            } else {
                throw new IllegalArgumentException("Unsupported attribute type: " + firstValue.getClass().getName());
            }
        }

    }

    public List<Association> getAssociations() {
        return Collections.unmodifiableList(associations);
    }

    public void setAssociations(String name, AssetId... assetIds) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(assetIds, "assetIds cannot be null");
        Association association = AssociationUtils.getOrCreateAssociation(associations, name);
        association.getAssociatedAssets().clear();
        for (AssetId assetId : assetIds) {
            association.getAssociatedAssets().add(assetId.toString());
        }
    }

    public void addAssociation(String associationName, AssetId assetId) {
        Objects.requireNonNull(associationName, "associationName cannot be null");
        Objects.requireNonNull(assetId, "assetId cannot be null");
        Association association = AssociationUtils.getOrCreateAssociation(associations, associationName);
        association.getAssociatedAssets().add(assetId.toString());
    }

    public List<Parent> getParents() {
        return Collections.unmodifiableList(parents);
    }

    public void setParent(String parentDefName, AssetId... parentIds) {
        Objects.requireNonNull(parentDefName, "parentDefName cannot be null");
        Objects.requireNonNull(parentIds, "parentIds cannot be null");
        Parent parent = ParentUtils.getOrCreateParent(parents, parentDefName);
        parent.getAssets().clear();
        for (AssetId parentId : parentIds) {
            parent.getAssets().add(parentId.toString());
        }
    }

    public void addParent(String parentDefName, AssetId parentId) {
        Objects.requireNonNull(parentDefName, "parentDefName cannot be null");
        Objects.requireNonNull(parentId, "parentId cannot be null");
        Parent parent = ParentUtils.getOrCreateParent(parents, parentDefName);
        parent.getAssets().add(parentId.toString());
    }

    private void setMultiValuedStringAttribute(String name, List<String> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getStringLists().clear();
        data.getStringLists().addAll(value);
    }

    private void setMultiValuedIntegerAttribute(String name, List<Integer> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getIntegerLists().clear();
        data.getIntegerLists().addAll(value);
    }

    private void setMultiValuedLongAttribute(String name, List<Long> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getLongLists().clear();
        data.getLongLists().addAll(value);
    }

    private void setMultiValuedDateAttribute(String name, List<Date> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getDateLists().clear();
        data.getDateLists().addAll(value);
    }

    private void setMultiValuedBigDecimalAttribute(String name, List<BigDecimal> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getDecimalLists().clear();
        data.getDecimalLists().addAll(value);
    }

    private void setMultiValuedBooleanAttribute(String name, List<Boolean> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getBooleanLists().clear();
        data.getBooleanLists().addAll(value);
    }

    private void setMultiValuedBlobAttribute(String name, List<Blob> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getBlobLists().clear();
        data.getBlobLists().addAll(value);
    }

    private void setMutliValuedDoubleAttribute(String name, List<Double> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getDoubleLists().clear();
        data.getDoubleLists().addAll(value);
    }

    private void setMultiValuedStructAttribute(String name, List<Struct> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getStructLists().clear();
        data.getStructLists().addAll(value);
    }

    private void setMultiValuedAssetIdAttribute(String name, List<AssetId> value) {
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.getStringLists().clear();
        value.stream()
                .map(AssetId::toString)
                .forEach(data.getStringLists()::add);

    }

    private void setStringAttribute(String name, String value) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(value, "value cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setStringValue(value);
    }

    private void setIntegerAttribute(String name, int value) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setIntegerValue(value);
    }

    private void setLongAttribute(String name, long value) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setLongValue(value);
    }

    private void setDateAttribute(String name, Date value) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(value, "value cannot be null");
        System.out.println("Setting date attribute: " + name + " = " + value);
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setDateValue(value);
    }

    private void setBigDecimalAttribute(String name, BigDecimal value) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setDecimalValue(value);
    }

    private void setBooleanAttribute(String name, boolean value) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setBooleanValue(value);
    }

    private void setBlobAttribute(String name, Blob value) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(value, "value cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setBlobValue(value);
    }

    private void setDoubleAttribute(String name, double value) {
        Objects.requireNonNull(name, "name cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setDoubleValue(value);
    }

    private void setStructAttribute(String name, Struct value) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(value, "value cannot be null");
        Attribute.Data data = AttributeUtils.getOrCreateAttributeData(attributes, name);
        data.setStructValue(value);
    }

    public AssetBean toAssetBean() {
        return assetBean;
    }

}
