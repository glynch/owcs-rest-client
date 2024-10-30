package io.github.glynch.owcs.rest.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fatwire.rest.beans.EnabledType;
import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SiteUser;
import com.fatwire.rest.beans.SiteUsersBean;

import io.github.glynch.owcs.rest.support.Roles;
import io.github.glynch.owcs.rest.support.Sites;
import io.github.glynch.owcs.rest.support.Users;

public class SiteBeanBuilder {

    private final SiteBean siteBean;
    private final EnabledTypesBean enabledTypesBean;
    private final SiteUsersBean siteUsersBean;

    private SiteBeanBuilder(String name) {
        this.siteBean = new SiteBean();
        this.siteBean.setName(name);
        this.enabledTypesBean = new EnabledTypesBean();
        this.siteBean.setEnabledAssetTypes(enabledTypesBean);
        SiteUsersBean siteUsersBean = new SiteUsersBean();
        this.siteUsersBean = siteUsersBean;
        siteBean.setSiteUsers(siteUsersBean);
    }

    public static SiteBeanBuilder builder(String name) {
        return new SiteBeanBuilder(name);
    }

    public static SiteBeanBuilder builder(Sites site) {
        return new SiteBeanBuilder(site.getName());
    }

    public SiteBeanBuilder description(String description) {
        siteBean.setDescription(description);
        return this;
    }

    public SiteBeanBuilder types(String... types) {
        Objects.requireNonNull(types, "types cannot be null");
        for (String type : types) {
            EnabledType enabledType = new EnabledType();
            enabledType.setName(type);
            enabledTypesBean.getTypes().add(enabledType);
        }
        return this;
    }

    public SiteBeanBuilder user(String user, String... roles) {
        Objects.requireNonNull(user, "user cannot be null");
        Objects.requireNonNull(roles, "roles cannot be null");
        SiteUser siteUser = new SiteUser();
        siteUser.setName(user);
        siteUser.getRoles().addAll(List.of(roles));
        siteUsersBean.getUsers().add(siteUser);
        return this;
    }

    public SiteBeanBuilder user(Users user, Roles... roles) {
        Objects.requireNonNull(user, "users cannot be null");
        Objects.requireNonNull(roles, "roles cannot be null");
        return user(user.getName(), Arrays.stream(roles).map(Roles::getName).toArray(String[]::new));
    }

    public SiteBean build() {
        return siteBean;
    }

}
