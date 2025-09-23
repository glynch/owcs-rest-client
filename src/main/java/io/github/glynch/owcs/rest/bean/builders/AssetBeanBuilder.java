package io.github.glynch.owcs.rest.bean.builders;

import java.util.Date;

import org.springframework.util.Assert;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Parent;
import com.fatwire.rest.beans.Webreference;
import com.openmarket.xcelerate.asset.AssetIdImpl;

import io.github.glynch.owcs.rest.bean.AssetBeanFacade;

public class AssetBeanBuilder {

    private final AssetBean assetBean;
    private final AssetBeanFacade assetBeanFacade;

    AssetBeanBuilder(String type, String name, String site) {
        this(type, null, name, site);
    }

    AssetBeanBuilder(String type, String subtype, String name, String site) {
        assetBean = new AssetBean();
        assetBeanFacade = new AssetBeanFacade(assetBean);
        assetBeanFacade.setAssetId(new AssetIdImpl(type, 0));
        assetBeanFacade.setName(name);
        assetBeanFacade.setSubtype(subtype);
        assetBeanFacade.addSites(site);
    }

    public AssetBeanBuilder description(String description) {
        assetBeanFacade.setDescription(description);
        return this;
    }

    public AssetBeanBuilder subtype(String subtype) {
        Assert.hasText(subtype, "subtype cannot be empty or null");
        assetBeanFacade.setSubtype(subtype);
        return this;
    }

    public AssetBeanBuilder startDate(Date startDate) {
        assetBeanFacade.setStartDate(startDate);
        return this;
    }

    public AssetBeanBuilder endDate(Date endDate) {
        assetBeanFacade.setEndDate(endDate);
        return this;
    }

    public AssetBeanBuilder tags(String tag, String... tags) {
        assetBeanFacade.addFwTags(tag, tags);
        return this;
    }

    public AssetBeanBuilder sites(String site, String... sites) {
        assetBeanFacade.addSites(site, sites);
        return this;
    }

    public AssetBeanBuilder webReferences(Webreference webreference, Webreference... webreferences) {
        assetBeanFacade.addWebReferences(webreference, webreferences);
        return this;
    }

    public AssetBeanBuilder parents(Parent parent, Parent... parents) {
        assetBeanFacade.addParents(parent, parents);
        return this;
    }

    public AssetBeanBuilder parents(String parentDefName, AssetId assetId, AssetId... assetIds) {
        assetBeanFacade.addParents(parentDefName, assetId, assetIds);
        return this;
    }

    public AssetBeanBuilder attribute(Attribute attribute) {
        Assert.notNull(attribute, "attribute cannot be null");
        assetBean.getAttributes().add(attribute);
        return this;
    }

    public AssetBeanBuilder attribute(String name, String value) {
        Assert.hasText(name, "name cannot be empty or null");
        assetBeanFacade.setStringAttribute(name, value);
        return this;
    }

    public AssetBeanBuilder attribute(String name, Integer value) {
        Assert.hasText(name, "name cannot be empty or null");
        assetBeanFacade.setIntegerAttribute(name, value);
        return this;
    }

    public AssetBeanBuilder attribute(String name, Long value) {
        Assert.hasText(name, "name cannot be empty or null");
        assetBeanFacade.setLongAttribute(name, value);
        return this;
    }

    public AssetBeanBuilder attribute(String name, Double value) {
        Assert.hasText(name, "name cannot be empty or null");
        assetBeanFacade.setDoubleAttribute(name, value);
        return this;
    }

    public AssetBeanBuilder attribute(String name, Date value) {
        Assert.hasText(name, "name cannot be empty or null");
        assetBeanFacade.setDateAttribute(name, value);
        return this;
    }

    public AssetBean build() {
        return assetBean;
    }

}
