package io.github.glynch.owcs.rest.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.openmarket.xcelerate.asset.AssetIdImpl;

import io.github.glynch.owcs.rest.support.Association;
import io.github.glynch.owcs.rest.support.Fields;
import io.github.glynch.owcs.rest.support.Sites;

public class AssetBeanBuilder {

    private final AssetBeanFacade assetBeanFacade;

    public static AssetBeanBuilder builder(String type, String name) {
        Objects.requireNonNull(type, "type cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        return new AssetBeanBuilder(type, null, name);
    }

    public static AssetBeanBuilder builder(String type, String subtype, String name) {
        Objects.requireNonNull(type, "type cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        return new AssetBeanBuilder(type, subtype, name);
    }

    private AssetBeanBuilder(String type, String subtype, String name) {
        assetBeanFacade = new AssetBeanFacade();
        assetBeanFacade.setAssetId(new AssetIdImpl(type, 0));
        assetBeanFacade.setSubtype(subtype);
        assetBeanFacade.setName(name);
    }

    /**
     * Set the subtype for the asset.
     * 
     * @param subtype
     * @return this
     */
    public AssetBeanBuilder subtype(String subtype) {
        assetBeanFacade.setSubtype(subtype);
        return this;
    }

    /**
     * Set the description for the asset.
     * 
     * @param description
     * @return this
     */
    public AssetBeanBuilder description(String description) {
        assetBeanFacade.setDescription(description);
        return this;
    }

    /**
     * Add sites to the asset.
     * 
     * @param sites
     * @return this
     */
    public AssetBeanBuilder sites(String... sites) {
        assetBeanFacade.addSites(sites);
        return this;
    }

    public AssetBeanBuilder sites(Sites... sites) {
        Objects.requireNonNull(sites, "sites cannot be null");
        assetBeanFacade.setSites(Arrays.stream(sites).map(Sites::getName).toArray(String[]::new));
        return this;
    }

    /**
     * Set the template for the asset.
     * 
     * @param template
     * @return this
     */
    public AssetBeanBuilder template(String template) {
        assetBeanFacade.setTemplate(template);
        return this;
    }

    /**
     * Add an attribute with a single or mutiple values.
     * 
     * If there is a single value it is assumed to be a single value attribute.
     * Otherwise it is assumed to be a multi-value attribute.
     * 
     * @param name
     * @param values
     * @return this
     */
    public AssetBeanBuilder attributes(String name, Object... values) {
        Objects.requireNonNull(values, "values cannot be null");
        assetBeanFacade.setAttribute(name, List.of(values));
        return this;
    }

    public AssetBeanBuilder attributes(Fields field, Object... values) {
        Objects.requireNonNull(values, "values cannot be null");
        return attributes(field.getName(), values);
    }

    public AssetBeanBuilder attribute(String name, Object value) {
        assetBeanFacade.setAttribute(name, value);
        return this;
    }

    public AssetBeanBuilder attribute(Fields field, Object value) {
        return attribute(field.getName(), value);
    }

    public AssetBeanBuilder parent(String parentDefName, AssetId... parentIds) {
        assetBeanFacade.setParent(parentDefName, parentIds);
        return this;
    }

    public AssetBeanBuilder association(String name, AssetId... assetIds) {
        assetBeanFacade.setAssociations(name, assetIds);
        return this;
    }

    public AssetBeanBuilder association(Association name, AssetId... assetIds) {
        return association(name.getName(), assetIds);
    }

    public AssetBeanBuilder startDate(Date startDate) {
        assetBeanFacade.setStartDate(startDate);
        return this;
    }

    public AssetBeanBuilder endDate(Date endDate) {
        assetBeanFacade.setEndDate(endDate);
        return this;
    }

    public AssetBean build() {
        return assetBeanFacade.toAssetBean();
    }

}
