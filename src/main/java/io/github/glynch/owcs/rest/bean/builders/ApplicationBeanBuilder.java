package io.github.glynch.owcs.rest.bean.builders;

import java.util.Arrays;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.SiteRoles;
import com.fatwire.rest.beans.View;
import com.fatwire.rest.beans.ViewTypeEnum;

public class ApplicationBeanBuilder {

    private final ApplicationBean applicationBean;

    ApplicationBeanBuilder(String name, LayoutTypeEnum layoutType) {
        this.applicationBean = new ApplicationBean();
        this.applicationBean.setId(0L);
        this.applicationBean.setName(name);
        this.applicationBean.setLayouttype(layoutType);
    }

    public ApplicationBeanBuilder description(String description) {
        applicationBean.setDescription(description);
        return this;
    }

    public ApplicationBeanBuilder shortDescription(String shortDescription) {
        applicationBean.setShortdescription(shortDescription);
        return this;
    }

    public ApplicationBeanBuilder tooltip(String tooltip) {
        Assert.hasText(tooltip, "tooltip cannot be null");
        applicationBean.setTooltip(tooltip);
        return this;
    }

    public ApplicationBeanBuilder iconUrl(String iconUrl) {
        Assert.hasText(iconUrl, "iconUrl cannot be null");
        applicationBean.setIconurl(iconUrl);
        return this;
    }

    public ApplicationBeanBuilder hoverIconUrl(String hoverIconUrl) {
        Assert.hasText(hoverIconUrl, "hoverIconUrl cannot be null");
        applicationBean.setIconurlhover(hoverIconUrl);
        return this;
    }

    public ApplicationBeanBuilder clickIconUrl(String clickIconUrl) {
        Assert.hasText(clickIconUrl, "clickIconUrl cannot be null");
        applicationBean.setIconurlclick(clickIconUrl);
        return this;
    }

    public ApplicationBeanBuilder activeIconUrl(String activeIconUrl) {
        Assert.hasText(activeIconUrl, "activeIconUrl cannot be null");
        applicationBean.setIconurlactive(activeIconUrl);
        return this;
    }

    public ApplicationBeanBuilder layoutUrl(String layoutUrl) {
        Assert.hasText(layoutUrl, "layoutUrl cannot be null");
        applicationBean.setLayouturl(layoutUrl);
        return this;
    }

    public ApplicationBeanBuilder view(View view) {
        Assert.notNull(view, "view cannot be null");
        Assert.hasText(view.getName(), "View name cannot be empty or null");
        Assert.notNull(view.getViewtype(), "View viewType cannot be null");
        applicationBean.getViews().add(view);
        return this;
    }

    public ApplicationBeanBuilder view(String name, ViewTypeEnum viewType, String description, String parentNode,
            String srcUrl,
            String includeContent, String javascriptContent) {
        View view = new View();
        view.setName(name);
        view.setViewtype(viewType);
        view.setDescription(description);
        view.setParentnode(parentNode);
        view.setSourceurl(srcUrl);
        view.setIncludecontent(includeContent);
        view.setJavascriptcontent(javascriptContent);
        return view(view);
    }

    public ApplicationBeanBuilder systemApplication() {
        applicationBean.setSystemapplication(true);
        return this;
    }

    public ApplicationBeanBuilder site(String site, String role, String... roles) {
        Assert.hasText(site, "site cannot be empty or null");
        Assert.hasText(role, "role cannot be empty or null");
        SiteRoles siteRoles = new SiteRoles();
        siteRoles.setSite(site);
        siteRoles.getRoles().add(role);
        if (roles != null && roles.length > 0) {
            siteRoles.getRoles().addAll(Arrays.asList(roles));
        }
        applicationBean.getSites().add(siteRoles);
        return this;
    }

    public ApplicationBean build() {
        if (applicationBean.getSites().size() == 0) {
            throw new IllegalStateException("Application must have at least 1 site role");
        }
        return applicationBean;
    }

}
