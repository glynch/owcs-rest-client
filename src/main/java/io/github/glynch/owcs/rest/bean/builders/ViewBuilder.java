package io.github.glynch.owcs.rest.bean.builders;

import com.fatwire.rest.beans.View;
import com.fatwire.rest.beans.ViewTypeEnum;

public class ViewBuilder {

    private View view;

    ViewBuilder(String name, ViewTypeEnum viewType) {
        view = new View();
        view.setName(name);
        view.setViewtype(viewType);
    }

    public ViewBuilder srcUrl(String srcUrl) {
        view.setSourceurl(srcUrl);
        return this;
    }

    public ViewBuilder includeContent(String includeContent) {
        view.setIncludecontent(includeContent);
        return this;
    }

    public ViewBuilder javascriptContent(String javaScriptContent) {
        view.setJavascriptcontent(javaScriptContent);
        return this;
    }

    public ViewBuilder parentNode(String parentNode) {
        view.setParentnode(parentNode);
        return this;
    }

    public View build() {
        return view;
    }

}
