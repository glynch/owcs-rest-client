package io.github.glynch.owcs.rest.bean.builders;

import java.util.Arrays;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.EnabledType;
import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SiteUser;
import com.fatwire.rest.beans.SiteUsersBean;

public class SiteBeanBuilder {

    private final SiteBean siteBean;

    SiteBeanBuilder(String name, String description) {
        this.siteBean = new SiteBean();
        this.siteBean.setName(name);
        this.siteBean.setDescription(description);
        this.siteBean.setEnabledAssetTypes(new EnabledTypesBean());
        this.siteBean.setSiteUsers(new SiteUsersBean());
    }

    public SiteBeanBuilder csPreview(String csPreview) {
        siteBean.setCSpreview(csPreview);
        return this;
    }

    public SiteBeanBuilder pubRoot(String pubRoot) {
        siteBean.setPubroot(pubRoot);
        return this;
    }

    public SiteBeanBuilder type(String type) {
        Assert.hasText(type, "type cannot be empty or null");
        EnabledType enabledType = new EnabledType();
        enabledType.setName(type);
        siteBean.getEnabledAssetTypes().getTypes().add(enabledType);
        return this;
    }

    public SiteBeanBuilder user(String user, String role, String... roles) {
        Assert.hasText(user, "user cannot be empty or null");
        Assert.hasText(role, "role cannot be empty or null");
        SiteUser siteUser = new SiteUser();
        siteUser.setName(user);
        siteUser.getRoles().add(role);
        if (roles != null && roles.length > 0) {
            siteUser.getRoles().addAll(Arrays.asList(roles));
        }
        siteBean.getSiteUsers().getUsers().add(siteUser);
        return this;
    }

    public SiteBean build() {
        return siteBean;
    }

}
