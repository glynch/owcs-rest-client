package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;
import java.util.Objects;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.GroupBean;
import com.fatwire.rest.beans.GroupsBean;
import com.fatwire.rest.beans.IndexConfigBean;
import com.fatwire.rest.beans.IndexConfigsBean;
import com.fatwire.rest.beans.RoleBean;
import com.fatwire.rest.beans.RolesBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SitesBean;
import com.fatwire.rest.beans.TimezoneBean;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UserDefBean;
import com.fatwire.rest.beans.UserLocalesBean;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.api.AuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.support.Indexes;
import io.github.glynch.owcs.rest.client.support.Roles;
import io.github.glynch.owcs.rest.client.support.Sites;
import io.github.glynch.owcs.rest.client.support.Types;

public class DefaultAuthenticatedRestClient implements AuthenticatedRestClient {

    private final AuthenticatedRestApi restApi;
    private final String baseUrl;

    DefaultAuthenticatedRestClient(AuthenticatedRestApi restApi, String baseUrl) {
        this.restApi = restApi;
        this.baseUrl = baseUrl;
    }

    @Override
    public AuthenticatedRestApi restApi() {
        return restApi;
    }

    @Override
    public String baseUrl() {
        return baseUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ?> resources(String version) {
        Objects.requireNonNull(version, "version cannot be null");
        return restApi.get(baseUrl + RESOURCES_VERSION_URI_TEMPLATE,
                builder -> builder.build(Map.of("version", version)),
                Map.class);
    }

    @Override
    public AssetTypesBean types() throws RestClientException {
        return restApi.get(baseUrl + TYPES_URI_TEMPLATE, AssetTypesBean.class);
    }

    @Override
    public AssetTypeBean type(Types type) throws RestClientException {
        return restApi.get(baseUrl + TYPE_URI_TEMPLATE, builder -> builder.build(Map.of("type", type.getName())),
                AssetTypeBean.class);
    }

    @Override
    public TypeResources types(Types type) throws RestClientException {
        return new DefaultTypeResources(this, type.getName());
    }

    @Override
    public IndexConfigsBean indexes() throws RestClientException {
        return restApi.get(baseUrl + INDEXES_URI_TEMPLATE, IndexConfigsBean.class);
    }

    @Override
    public IndexConfigBean index(Indexes index) throws RestClientException {
        Objects.requireNonNull(index, "index cannot be null");
        return restApi.get(baseUrl + INDEX_URI_TEMPLATE, builder -> builder.build(Map.of("index", index.getName())),
                IndexConfigBean.class);
    }

    @Override
    public SitesBean sites() throws RestClientException {
        return restApi.get(baseUrl + SITES_URI_TEMPLATE, SitesBean.class);
    }

    @Override
    public SiteBean site(Sites site) throws RestClientException {
        Objects.requireNonNull(site, "site cannot be null");
        return restApi.get(baseUrl + SITE_URI_TEMPLATE, builder -> builder.build(Map.of("site", site.getName())),
                SiteBean.class);
    }

    @Override
    public SiteResources sites(Sites site) throws RestClientException {
        return new DefaultSiteResources(this, site.getName());
    }

    @Override
    public RolesBean roles() throws RestClientException {
        return restApi.get(baseUrl + ROLES_URI_TEMPLATE, RolesBean.class);
    }

    @Override
    public RoleBean role(Roles role) throws RestClientException {
        Objects.requireNonNull(role, "role cannot be null");
        return restApi.get(baseUrl + ROLE_URI_TEMPLATE, builder -> builder.build(Map.of("role", role.getName())),
                RoleBean.class);
    }

    @Override
    public ApplicationsBean applications() throws RestClientException {
        return restApi.get(baseUrl + APPLICATIONS_URI_TEMPLATE, ApplicationsBean.class);
    }

    @Override
    public ApplicationBean application(long id) throws RestClientException {
        Objects.requireNonNull(id, "id cannot be null");
        return restApi.get(baseUrl + APPLICATION_URI_TEMPLATE, builder -> builder.build(Map.of("id", id)),
                ApplicationBean.class);
    }

    @Override
    public UsersBean users() throws RestClientException {
        return restApi.get(baseUrl + USERS_URI_TEMPLATE, UsersBean.class);
    }

    @Override
    public UserBean user(String user) throws RestClientException {
        Objects.requireNonNull(user, "user cannot be null");
        return restApi.get(baseUrl + USER_URI_TEMPLATES, builder -> builder.build(Map.of("user", user)),
                UserBean.class);
    }

    @Override
    public UserLocalesBean userLocales() throws RestClientException {
        return restApi.get(baseUrl + USER_LOCALES_URI_TEMPLATE, UserLocalesBean.class);
    }

    @Override
    public UserDefBean userDef() throws RestClientException {
        return restApi.get(baseUrl + USER_DEF_URI_TEMPLATE, UserDefBean.class);
    }

    @Override
    public AclsBean acls() throws RestClientException {
        return restApi.get(baseUrl + ACLS_URI_TEMPLATE, AclsBean.class);
    }

    @Override
    public GroupsBean groups() throws RestClientException {
        return restApi.get(baseUrl + GROUPS_URI_TEMPLATE, GroupsBean.class);
    }

    @Override
    public GroupBean group(String group) throws RestClientException {
        Objects.requireNonNull(group, "group cannot be null");
        return restApi.get(baseUrl + GROUP_URI_TEMPLATE, builder -> builder.build(Map.of("group", group)),
                GroupBean.class);
    }

    @Override
    public TimezoneBean timeZone() throws RestClientException {
        return restApi.get(baseUrl + TIMEZONE_URI_TEMPLATE, TimezoneBean.class);
    }

    @Override
    public DeviceBean currentDevice() throws RestClientException {
        return restApi.get(baseUrl + CURRENT_DEVICE_URI_TEMPLATE, DeviceBean.class);
    }

}
