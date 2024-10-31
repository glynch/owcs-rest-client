package io.github.glynch.owcs.rest.client.bean;

import java.util.Objects;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.View;
import com.fatwire.rest.beans.ViewTypeEnum;

import io.github.glynch.owcs.rest.client.bean.utils.AssetIds;
import io.github.glynch.owcs.rest.client.bean.utils.AttributeUtils;

public class ViewAdapter extends View {

    private static String PARENT_NODE = "parentnode";
    private static String VIEW_TYPE = "viewtype";
    private static String SRC_URL = "srcurl";
    private static String SCRIPT = "script";
    private static String CONTENT = "content";

    public ViewAdapter(AssetBean viewBean) {
        Objects.requireNonNull(viewBean, "viewBean cannot be null");
        AssetId assetId = AssetIds.of(viewBean.getId());
        if (!"FW_View".equals(assetId.getType())) {
            throw new IllegalArgumentException("AssetBean is not an 'FW_View'");
        }
        setId(AssetIds.of(viewBean.getId()).getId());
        setName(viewBean.getName());
        setDescription(viewBean.getDescription());
        setSourceurl(AttributeUtils.asString(viewBean.getAttributes(), SRC_URL));
        setJavascriptcontent(AttributeUtils.asString(viewBean.getAttributes(), SCRIPT));
        setIncludecontent(AttributeUtils.asString(viewBean.getAttributes(), CONTENT));
        setParentnode(AttributeUtils.asString(viewBean.getAttributes(), PARENT_NODE));
        setViewtype(ViewTypeEnum.fromValue(AttributeUtils.asString(viewBean.getAttributes(), VIEW_TYPE)));
    }

}
