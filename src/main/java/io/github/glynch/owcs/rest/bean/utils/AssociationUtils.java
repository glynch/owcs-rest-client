package io.github.glynch.owcs.rest.bean.utils;

import java.util.List;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;

public class AssociationUtils {

    private AssociationUtils() {

    }

    public static List<Association> getAssociations(AssetBean assetBean) {
        Assert.notNull(assetBean, "assetBean cannot be null");
        return assetBean.getAssociations().getAssociations();
    }

    public static Association getAssociation(AssetBean assetBean, String name) {
        List<Association> associations = getAssociations(assetBean);
        return associations.stream()
                .filter(a -> name.equals(a.getName()))
                .findFirst()
                .orElse(null);
    }

    public static Association getOrCreateAssociation(AssetBean assetBean, String name) {
        Assert.hasText(name, "name cannot be empty or null");
        Association association = getAssociation(assetBean, name);
        if (association == null) {
            association = new Association();
            association.setName(name);
            assetBean.getAssociations().getAssociations().add(association);
        }
        return association;
    }

    public static List<String> getAssociatedAssets(AssetBean assetBean, String name) {
        Association association = getOrCreateAssociation(assetBean, name);
        return association.getAssociatedAssets();
    }

}
