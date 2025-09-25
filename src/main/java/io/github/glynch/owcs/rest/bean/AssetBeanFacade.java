package io.github.glynch.owcs.rest.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.assetapi.data.BasicAssetDataReadStrategy;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Attribute.Data;
import com.fatwire.rest.beans.Blob;
import com.fatwire.rest.beans.Parent;
import com.fatwire.rest.beans.Struct;
import com.fatwire.rest.beans.Webreference;
import com.openmarket.xcelerate.interfaces.IAsset;

import io.github.glynch.owcs.rest.bean.utils.AssetIds;
import io.github.glynch.owcs.rest.bean.utils.AssociationUtils;
import io.github.glynch.owcs.rest.bean.utils.AttributeUtils;
import io.github.glynch.owcs.rest.bean.utils.ParentUtils;

/**
 * A facade for modifying an AssetBean
 * 
 */
public class AssetBeanFacade {

    private final AssetBean assetBean;
    private static final String TEMPLATE = IAsset.TEMPLATE;
    private static final String STARTDATE = IAsset.STARTDATE;
    private static final String ENDDATE = IAsset.ENDDATE;
    private static final String UID = IAsset.UID;
    private static final String FW_TAGS = IAsset.FWTAGS;

    public static final List<String> SYSTEM_ATTRIBUTES = BasicAssetDataReadStrategy.basicStandAttributes;

    public AssetBeanFacade(AssetBean assetBean) {
        Assert.notNull(assetBean, "assetBean cannot be null");
        this.assetBean = assetBean;
    }

    public String getId() {
        return assetBean.getId();
    }

    public void setAssetId(AssetId assetId) {
        assetBean.setId(assetId.toString());
    }

    public AssetId getAssetId() {
        return AssetIds.of(assetBean.getId());
    }

    public void setName(String name) {
        Assert.hasText(name, "name cannot be empty or null");
        assetBean.setName(name);
    }

    public String getName() {
        return assetBean.getName();
    }

    public void setDescription(String description) {
        Assert.hasText(description, "description cannot be empty or null");
        assetBean.setDescription(description);
    }

    public String getDescription() {
        return assetBean.getDescription();
    }

    public String getStatus() {
        return assetBean.getStatus();
    }

    public void setSubtype(String subtype) {
        assetBean.setSubtype(subtype);
    }

    public String getSubtype() {
        return assetBean.getSubtype();
    }

    public void setTemplate(String template) {
        setStringAttribute(TEMPLATE, template);
    }

    public String getTemplate() {
        return AttributeUtils.asString(assetBean, TEMPLATE);
    }

    public String getCreatedBy() {
        return assetBean.getCreatedby();
    }

    public String getUpdatedBy() {
        return assetBean.getUpdatedby();

    }

    public void setStartDate(Date startDate) {
        setDateAttribute(STARTDATE, startDate);
    }

    public Date getStartDate() {
        return AttributeUtils.asDate(assetBean, STARTDATE);
    }

    public void setEndDate(Date endDate) {
        setDateAttribute(ENDDATE, endDate);
    }

    public Date getEndDate() {
        return AttributeUtils.asDate(assetBean, ENDDATE);
    }

    public void setFwTags(String tag, String tags) {
        Assert.hasText(tag, "tag cannot be empty or null");
        List<String> fwTags = AttributeUtils.asStringList(assetBean, FW_TAGS);
        fwTags.clear();
        fwTags.add(tag);
        if (tags != null && tags.length() > 0) {
            fwTags.addAll(Arrays.asList(tags));
        }
    }

    public void addFwTags(String tag, String... tags) {
        List<String> fwTags = AttributeUtils.asStringList(assetBean, FW_TAGS);
        fwTags.add(tag);
        if (tags != null && tags.length > 0) {
            fwTags.addAll(Arrays.asList(tags));
        }
    }

    public List<String> getFwTags() {
        return Collections.unmodifiableList(AttributeUtils.asStringList(assetBean, FW_TAGS));
    }

    public void addSites(String site, String... sites) {
        Assert.hasText(site, "site cannot be empty or null");
        assetBean.getPublists().add(site);
        if (sites != null && sites.length > 0) {
            assetBean.getPublists().addAll(Arrays.asList(sites));
        }
    }

    public void setSites(String site, String... sites) {
        Assert.hasText(site, "site cannot be empty or null");
        assetBean.getPublists().clear();
        addSites(site, sites);
    }

    public List<String> getSites() {
        return Collections.unmodifiableList(assetBean.getPublists());
    }

    public String getFwUid() {
        return AttributeUtils.asString(assetBean, UID);

    }

    public void addWebReferences(Webreference webReference, Webreference... webreferences) {
        Assert.notNull(webreferences, "webRreference cannot be null");
        AttributeUtils.getWebReferences(assetBean).add(webReference);
        if (webreferences != null && webreferences.length > 0) {
            AttributeUtils.getWebReferences(assetBean).addAll(Arrays.asList(webreferences));
        }

    }

    public void setWebReferences(Webreference webReference, Webreference... webreferences) {
        AttributeUtils.getWebReferences(assetBean).clear();
        addWebReferences(webReference, webreferences);
    }

    public List<Webreference> getWebReferences() {
        return AttributeUtils.getWebReferences(assetBean);
    }

    public Webreference getDefaultWebreference() {
        List<Webreference> webReferences = AttributeUtils.getWebReferences(assetBean);
        return webReferences.stream().filter(webReference -> webReference.isDefault()).findFirst().orElse(null);
    }

    public AssetId getFlexTemplateId() {
        return AttributeUtils.asAssetId(assetBean, "flextemplateid");
    }

    public AssetId getFlexGroupTemplateId() {
        return AttributeUtils.asAssetId(assetBean, "flexgrouptemplateid");
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(assetBean.getAttributes());
    }

    public void addAttributes(Attribute attribute, Attribute... attributes) {
        Assert.notNull(attribute, "attribute cannot be null");
        assetBean.getAttributes().add(attribute);
        if (attributes != null && attributes.length > 0) {
            assetBean.getAttributes().addAll(Arrays.asList(attributes));
        }
    }

    public void setAttributes(Attribute attribute, Attribute... attributes) {
        assetBean.getAttributes().clear();
        addAttributes(attribute, attributes);
    }

    public void addAssociations(Association association, Association... associations) {
        Assert.notNull(associations, "association cannot be null");
        AssociationUtils.getAssociations(assetBean).add(association);
        if (ArrayUtils.isNotEmpty(associations)) {
            AssociationUtils.getAssociations(assetBean).addAll(Arrays.asList(associations));
        }
    }

    public void setAssociations(Association association, Association... associations) {
        Assert.notNull(associations, "association cannot be null");
        AssociationUtils.getAssociations(assetBean).clear();
        addAssociations(association, associations);
    }

    public void addAssociations(String name, AssetId id, AssetId... ids) {
        List<String> associatedAssets = AssociationUtils.getAssociatedAssets(assetBean, name);
        associatedAssets.add(id.toString());
        if (ids != null && ids.length > 0) {
            for (AssetId assetId : ids) {
                associatedAssets.add(assetId.toString());
            }
        }
    }

    public void setAssociations(String name, AssetId id, AssetId... ids) {
        List<String> associatedAssets = AssociationUtils.getAssociatedAssets(assetBean, name);
        associatedAssets.clear();
        addAssociations(name, id, ids);
    }

    public List<Association> getAssociations() {
        return Collections.unmodifiableList(AssociationUtils.getAssociations(assetBean));
    }

    public Association getAssociation(String name) {
        return AssociationUtils.getOrCreateAssociation(assetBean, name);
    }

    public void addParents(String parentDefName, AssetId id, AssetId... ids) {
        Assert.notNull(id, "id cannot be null");
        Parent parent = ParentUtils.getOrCreateParent(assetBean, parentDefName);
        parent.getAssets().add(id.toString());
        if (ArrayUtils.isNotEmpty(ids)) {
            for (AssetId assetId : ids) {
                parent.getAssets().add(assetId.toString());
            }
        }

    }

    public void setParents(String parentDefName, AssetId id, AssetId... ids) {
        ParentUtils.getParent(assetBean, parentDefName).getAssets().clear();
        addParents(parentDefName, id, ids);
    }

    public void addParents(Parent parent, Parent... parents) {
        Assert.notNull(parents, "parent cannot be null");
        ParentUtils.getParents(assetBean).add(parent);
        if (ArrayUtils.isNotEmpty(parents)) {
            ParentUtils.getParents(assetBean).addAll(Arrays.asList(parents));
        }
    }

    public void setParents(Parent parent, Parent... parents) {
        ParentUtils.getParents(assetBean).clear();
        addParents(parent, parents);
    }

    public List<Parent> getParents(AssetBean assetBean) {
        return Collections.unmodifiableList(ParentUtils.getParents(assetBean));
    }

    public Parent getParent(String parentDefName) {
        return ParentUtils.getOrCreateParent(assetBean, parentDefName);
    }

    public void setStringAttribute(String name, String value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setStringValue(value);
    }

    public String getStringAttribute(String name) {
        return AttributeUtils.asString(assetBean, name);
    }

    public void setIntegerAttribute(String name, Integer value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setIntegerValue(value);
    }

    public Integer getIntegerAttribute(String name) {
        return AttributeUtils.asInteger(assetBean, name);
    }

    public void setLongAttribute(String name, Long value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setLongValue(value);
    }

    public Long getLongAttribute(String name) {
        return AttributeUtils.asLong(assetBean, name);
    }

    public void setDateAttribute(String name, Date value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setDateValue(value);
    }

    public Date getDateAttribute(String name) {
        return AttributeUtils.asDate(assetBean, name);
    }

    public void setBigDecimalAttribute(String name, BigDecimal value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setDecimalValue(value);
    }

    public void setBooleanAttribute(String name, boolean value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setBooleanValue(value);
    }

    public BigDecimal getBigDecimalAttribute(String name) {
        return AttributeUtils.asBigDecimal(assetBean, name);
    }

    public void setBlobAttribute(String name, Blob value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setBlobValue(value);
    }

    public Blob getBlobAttribute(String name) {
        return AttributeUtils.asBlob(assetBean, name);
    }

    public void setDoubleAttribute(String name, Double value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setDoubleValue(value);
    }

    public Double getDoubleAttribute(String name) {
        return AttributeUtils.asDouble(assetBean, name);
    }

    public void setStructAttribute(String name, Struct value) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.setStructValue(value);
    }

    public Struct getStructAttribute(String name) {
        return AttributeUtils.asStruct(assetBean, name);
    }

    public void setAssetAttribute(String name, AssetId value) {
        setStringAttribute(name, value != null ? value.toString() : null);
    }

    public void setMultiValuedStringAttribute(String name, List<String> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getStringLists().clear();
        data.getStringLists().addAll(values);
    }

    public List<String> getMultiValuedStringAttribute(String name) {
        return AttributeUtils.asStringList(assetBean, name);
    }

    public void setMultiValuedIntegerAttribute(String name, List<Integer> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getIntegerLists().clear();
        data.getIntegerLists().addAll(values);
    }

    public List<Integer> getMultiValuedIntegerAttribute(String name) {
        return AttributeUtils.asIntegerList(assetBean, name);
    }

    public void setMultiValuedLongAttribute(String name, List<Long> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getLongLists().clear();
        data.getLongLists().addAll(values);
    }

    public List<Long> getMultiValuedLongAttribute(String name) {
        return AttributeUtils.asLongList(assetBean, name);
    }

    public void setMultiValuedDateAttribute(String name, List<Date> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getDateLists().clear();
        data.getDateLists().addAll(values);
    }

    public List<Date> getMultiValuedDateAttribute(String name) {
        return AttributeUtils.asDateList(assetBean, name);
    }

    public void setMultiValuedBigDecimalAttribute(String name, List<BigDecimal> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getDecimalLists().clear();
        data.getDecimalLists().addAll(values);
    }

    public List<BigDecimal> getMultiValuedBigDecimalAttribute(String name) {
        return AttributeUtils.asBigDecimalList(assetBean, name);
    }

    public void setMultiValuedBlobAttribute(String name, List<Blob> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getBlobLists().clear();
        data.getBlobLists().addAll(values);
    }

    public List<Blob> getMultiValuedBlobAttribute(String name) {
        return AttributeUtils.asBlobList(assetBean, name);
    }

    public void setMultiValuedDoubleAttribute(String name, List<Double> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getDoubleLists().clear();
        data.getDoubleLists().addAll(values);
    }

    public List<Double> getMultiValuedDoubleAttribute(String name) {
        return AttributeUtils.asDoubleList(assetBean, name);
    }

    public void setMultiValuedStructAttribute(String name, List<Struct> values) {
        Data data = AttributeUtils.getOrCreateAttributeData(assetBean, name);
        data.getStructLists().clear();
        data.getStructLists().addAll(values);
    }

    public List<Struct> getMultiValuedStructAttribute(String name) {
        return AttributeUtils.asStructList(assetBean, name);
    }

    public void setMultiValuedAssetAttribute(String name, List<AssetId> values) {
        if (values != null) {
            setMultiValuedStringAttribute(name,
                    values.stream().map(value -> value.toString()).collect(Collectors.toList()));
        } else {
            setMultiValuedStringAttribute(name, new ArrayList<>());
        }

    }

    public List<AssetId> getMultiValuedAssetAttribute(String name) {
        return AttributeUtils.asAssetIdList(assetBean, name);
    }

}
