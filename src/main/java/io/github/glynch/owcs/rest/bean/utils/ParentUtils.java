package io.github.glynch.owcs.rest.bean.utils;

import java.util.List;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Parent;

public class ParentUtils {

    private ParentUtils() {

    }

    public static List<Parent> getParents(AssetBean assetBean) {
        return assetBean.getParents();
    }

    public static Parent getParent(AssetBean assetBean, String parentDefName) {
        List<Parent> parents = getParents(assetBean);
        return parents.stream()
                .filter(p -> parentDefName.equals(p.getParentDefName()))
                .findFirst().orElse(null);
    }

    public static Parent getOrCreateParent(AssetBean assetBean, String parentDefName) {
        Assert.hasText(parentDefName, "parentDefName cannot be empty or null");
        Parent parent = getParent(assetBean, parentDefName);
        if (parent == null) {
            parent = new Parent();
            parent.setParentDefName(parentDefName);
            getParents(assetBean).add(parent);
        }
        return parent;
    }

}
