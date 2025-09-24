package io.github.glynch.owcs.rest.bean.utils;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fatwire.rest.beans.AssetBean;

public class AssetBeanUtils {

    private AssetBeanUtils() {

    }

    public static String getSite(AssetBean assetBean) {
        List<String> sites = assetBean.getPublists();
        String site = null;
        if (!CollectionUtils.isEmpty(sites)) {
            site = sites.get(0);
        }

        return site;
    }

}
