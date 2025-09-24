package io.github.glynch.owcs.rest.bean.builders;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.IndexFieldTypeEnum;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.ViewTypeEnum;

public class Builders {

    public static IndexConfigBeanBuilder indexConfigBeanBuilder(String name) {
        return new IndexConfigBeanBuilder(name);
    }

    public static IndexFieldDescriptorBuilder indexFieldDescriptorBuilder(String name, IndexFieldTypeEnum type) {
        return new IndexFieldDescriptorBuilder(name, type);
    }

    public static ApplicationBeanBuilder applicationBeanBuilder(String name, LayoutTypeEnum layoutType) {
        Assert.hasText(name, " cannot be empty or null");
        Assert.notNull(layoutType, "layoutType cannort be null");
        return new ApplicationBeanBuilder(name, layoutType);
    }

    public static ViewBuilder viewBuilder(String name, ViewTypeEnum viewType) {
        return new ViewBuilder(name, viewType);
    }

    public static AssetTypeBeanBuilder assetTypeBeanBuilder(String name, String subtype) {
        Assert.hasText(name, " cannot be empty or null");
        return new AssetTypeBeanBuilder(name, subtype);
    }

    public static AssetBeanBuilder assetBeanBuilder(String type, String name, String site) {
        return new AssetBeanBuilder(type, name, site);
    }

    public static AssetBeanBuilder assetBeanBuilder(String type, String subtype, String name, String site) {
        return new AssetBeanBuilder(type, subtype, name, site);
    }

}
