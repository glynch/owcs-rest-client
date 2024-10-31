package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;
import java.util.Objects;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.GroupBean;
import com.fatwire.rest.beans.GroupsBean;
import com.fatwire.rest.beans.IndexConfigBean;
import com.fatwire.rest.beans.IndexConfigsBean;
import com.fatwire.rest.beans.ListKeyValuePairs;
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
import io.github.glynch.owcs.rest.client.authenticated.search.LuceneSearchQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.VisitorHistoryQuery;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.types.Indexes;
import io.github.glynch.owcs.rest.client.types.Roles;
import io.github.glynch.owcs.rest.client.types.Sites;
import io.github.glynch.owcs.rest.client.types.Types;
import io.github.glynch.owcs.rest.client.types.Users;
import io.github.glynch.owcs.rest.client.types.Versions;

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
    public Map<String, ?> resources(Versions version) {
        Objects.requireNonNull(version, "version cannot be null");
        return restApi.get(baseUrl + RESOURCES_VERSION_URI_TEMPLATE,
                builder -> builder.build(Map.of("version", version.getName())),
                Map.class);
    }

    @Override
    public AssetsBean search(LuceneSearchQuery query) throws RestClientException {
        Objects.requireNonNull(query, "query cannot be null");
        return restApi.get(baseUrl + GLOBAL_SEARCH_URI_TEMPLATE,
                builder -> builder.queryParams(query.queryParams()).build(), AssetsBean.class);
    }

    @Override
    public AssetTypesBean types() throws RestClientException {
        return restApi.get(baseUrl + TYPES_URI_TEMPLATE, AssetTypesBean.class);
    }

    @Override
    public TypeResources type(String type) {
        Objects.requireNonNull(type, "type cannot be null");
        return new DefaultTypeResources(this, type);
    }

    @Override
    public TypeResources type(Types type) {
        Objects.requireNonNull(type, "type cannot be null");
        return type(type.getName());
    }

    @Override
    public AssetTypeBean put(AssetTypeBean assetTypeBean) throws RestClientException {
        Objects.requireNonNull(assetTypeBean, "assetTypeBean cannot be null");
        Objects.requireNonNull(assetTypeBean.getName(), "assetTypeBean.name cannot be null");
        return restApi.put(baseUrl + TYPE_URI_TEMPLATE,
                builder -> builder.build(Map.of("type", assetTypeBean.getName())),
                assetTypeBean,
                AssetTypeBean.class);
    }

    @Override
    public IndexConfigsBean indexes() throws RestClientException {
        return restApi.get(baseUrl + INDEXES_URI_TEMPLATE, IndexConfigsBean.class);
    }

    @Override
    public IndexConfigBean index(String index) throws RestClientException {
        Objects.requireNonNull(index, "index cannot be null");
        return restApi.get(baseUrl + INDEX_URI_TEMPLATE, builder -> builder.build(Map.of("index", index)),
                IndexConfigBean.class);
    }

    @Override
    public IndexConfigBean index(Indexes index) throws RestClientException {
        Objects.requireNonNull(index, "index cannot be null");
        return index(index.getName());
    }

    @Override
    public SiteResources site(String site) {
        Objects.requireNonNull(site, "site cannot be null");
        return new DefaultSiteResources(this, site);
    }

    @Override
    public SiteResources site(Sites site) {
        Objects.requireNonNull(site, "site cannot be null");
        return site(site.getName());
    }

    @Override
    public SitesBean sites() throws RestClientException {
        return restApi.get(baseUrl + SITES_URI_TEMPLATE, SitesBean.class);
    }

    @Override
    public SiteBean put(SiteBean siteBean) throws RestClientException {
        Objects.requireNonNull(siteBean, "siteBean cannot be null");
        return restApi.put(baseUrl + SITE_URI_TEMPLATE, builder -> builder.build(Map.of("site", 0)),
                siteBean,
                SiteBean.class);
    }

    @Override
    public SiteBean post(SiteBean siteBean) throws RestClientException {
        Objects.requireNonNull(siteBean, "siteBean cannot be null");
        return restApi.post(baseUrl + SITE_URI_TEMPLATE, builder -> builder.build(Map.of("site", siteBean.getId())),
                siteBean,
                SiteBean.class);
    }

    @Override
    public RolesBean roles() throws RestClientException {
        return restApi.get(baseUrl + ROLES_URI_TEMPLATE, RolesBean.class);
    }

    @Override
    public RoleBean role(String role) throws RestClientException {
        Objects.requireNonNull(role, "role cannot be null");
        return restApi.get(baseUrl + ROLE_URI_TEMPLATE, builder -> builder.build(Map.of("role", role)),
                RoleBean.class);
    }

    @Override
    public RoleBean role(Roles role) throws RestClientException {
        Objects.requireNonNull(role, "role cannot be null");
        return role(role.getName());
    }

    @Override
    public ApplicationsBean applications() throws RestClientException {
        return restApi.get(baseUrl + APPLICATIONS_URI_TEMPLATE, ApplicationsBean.class);
    }

    @Override
    public ApplicationResources application(long id) throws RestClientException {
        return new DefaultApplicationResources(this, id);
    }

    @Override
    public ApplicationBean put(ApplicationBean application) throws RestClientException {
        Objects.requireNonNull(application, "application cannot be null");
        return restApi.put(baseUrl + APPLICATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("id", 0)),
                application,
                ApplicationBean.class);
    }

    @Override
    public ApplicationBean post(ApplicationBean application) throws RestClientException {
        Objects.requireNonNull(application, "application cannot be null");
        return restApi.post(baseUrl + APPLICATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("id", application.getId())),
                application,
                ApplicationBean.class);
    }

    @Override
    public UsersBean users() throws RestClientException {
        return restApi.get(baseUrl + USERS_URI_TEMPLATE, UsersBean.class);
    }

    @Override
    public UserResources user(String user) {
        Objects.requireNonNull(user, "user cannot be null");
        return new DefaultUserResources(this, user);
    }

    @Override
    public UserResources user(Users user) {
        Objects.requireNonNull(user, "user cannot be null");
        return user(user.getName());
    }

    @Override
    public UserBean put(UserBean user) throws RestClientException {
        Objects.requireNonNull(user, "user cannot be null");
        return restApi.put(baseUrl + USER_URI_TEMPLATE, builder -> builder.build(Map.of("user", user.getName())),
                user,
                UserBean.class);
    }

    @Override
    public UserBean post(UserBean user) throws RestClientException {
        Objects.requireNonNull(user, "user cannot be null");
        return restApi.post(baseUrl + USER_URI_TEMPLATE, builder -> builder.build(Map.of("user", user.getName())),
                user,
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

    @Override
    public ListKeyValuePairs visitorHistory(VisitorHistoryQuery query) throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitorHistory'");
    }

}
