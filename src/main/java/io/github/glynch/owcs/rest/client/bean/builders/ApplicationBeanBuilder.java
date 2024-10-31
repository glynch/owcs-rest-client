package io.github.glynch.owcs.rest.client.bean.builders;

import java.util.List;
import java.util.Objects;

import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.SiteRoles;
import com.fatwire.rest.beans.View;

import io.github.glynch.owcs.rest.client.types.Roles;
import io.github.glynch.owcs.rest.client.types.Sites;

public class ApplicationBeanBuilder {

    private final ApplicationBean applicationBean;

    private ApplicationBeanBuilder(String name, LayoutTypeEnum layoutType) {
        this.applicationBean = new ApplicationBean();
        this.applicationBean.setName(name);
        this.applicationBean.setLayouttype(layoutType);
    }

    public static ApplicationBeanBuilder builder(String name, LayoutTypeEnum layoutType) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(layoutType, "layoutType cannot be null");
        return new ApplicationBeanBuilder(name, layoutType);
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
        Objects.requireNonNull(tooltip, "tooltip cannot be null");
        applicationBean.setTooltip(tooltip);
        return this;
    }

    public ApplicationBeanBuilder iconUrl(String iconUrl) {
        Objects.requireNonNull(iconUrl, "iconUrl cannot be null");
        applicationBean.setIconurl(iconUrl);
        return this;
    }

    public ApplicationBeanBuilder hoverIconUrl(String hoverIconUrl) {
        Objects.requireNonNull(hoverIconUrl, "hoverIconUrl cannot be null");
        applicationBean.setIconurlhover(hoverIconUrl);
        return this;
    }

    public ApplicationBeanBuilder clickIconUrl(String clickIconUrl) {
        Objects.requireNonNull(clickIconUrl, "clickIconUrl cannot be null");
        applicationBean.setIconurlclick(clickIconUrl);
        return this;
    }

    public ApplicationBeanBuilder activeIconUrl(String activeIconUrl) {
        Objects.requireNonNull(activeIconUrl, "activeIconUrl cannot be null");
        applicationBean.setIconurlactive(activeIconUrl);
        return this;
    }

    public ApplicationBeanBuilder layoutUrl(String layoutUrl) {
        Objects.requireNonNull(layoutUrl, "layoutUrl cannot be null");
        applicationBean.setLayouturl(layoutUrl);
        return this;
    }

    public ApplicationBeanBuilder views(View... views) {
        Objects.requireNonNull(views, "views cannot be null");
        applicationBean.getViews().addAll(List.of(views));
        return this;

    }

    public ApplicationBeanBuilder site(String site, String... roles) {
        Objects.requireNonNull(site, "site cannot be null");
        Objects.requireNonNull(roles, "roles cannot be null");
        SiteRoles siteRoles = new SiteRoles();
        siteRoles.setSite(site);
        siteRoles.getRoles().addAll(List.of(roles));
        applicationBean.getSites().add(siteRoles);
        return this;
    }

    public ApplicationBeanBuilder site(Sites site, Roles... roles) {
        Objects.requireNonNull(site, "site cannot be null");
        Objects.requireNonNull(roles, "roles cannot be null");
        return site(site.getName(), List.of(roles).stream().map(Roles::getName).toArray(String[]::new));
    }

    public ApplicationBeanBuilder systemApplication() {
        applicationBean.setSystemapplication(true);
        return this;
    }

    public ApplicationBean build() {
        return applicationBean;
    }

}
