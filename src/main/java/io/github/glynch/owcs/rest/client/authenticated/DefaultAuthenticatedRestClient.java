package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;
import java.util.Objects;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.GroupBean;
import com.fatwire.rest.beans.GroupsBean;
import com.fatwire.rest.beans.IndexConfigsBean;
import com.fatwire.rest.beans.RolesBean;
import com.fatwire.rest.beans.SitesBean;
import com.fatwire.rest.beans.TimezoneBean;
import com.fatwire.rest.beans.UserDefBean;
import com.fatwire.rest.beans.UserLocalesBean;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.api.AuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultAuthenticatedRestClient implements AuthenticatedRestClient {

    private final AuthenticatedRestApi restApi;
    private final String baseUrl;

    DefaultAuthenticatedRestClient(AuthenticatedRestApi restApi, String baseUrl) {
        this.restApi = restApi;
        this.baseUrl = baseUrl;
    }

    @Override
    public Map<String, ?> resources(String version) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resources'");
    }

    @Override
    public AssetTypesBean types() throws RestClientException {
        return restApi.get(baseUrl + ALL_ASSETTYPES_URI_TEMPLATE, AssetTypesBean.class);
    }

    @Override
    public AssetTypeResources types(String type) throws RestClientException {
        return new DefaultAssetTypeResources(restApi, baseUrl, type);
    }

    @Override
    public IndexConfigsBean indexes() throws RestClientException {
        return restApi.get(baseUrl + INDEXES_URI_TEMPLATE, IndexConfigsBean.class);
    }

    @Override
    public SitesBean sites() throws RestClientException {
        return restApi.get(baseUrl + ALL_SITES_URI_TEMPLATE, SitesBean.class);
    }

    @Override
    public RolesBean roles() throws RestClientException {
        return restApi.get(baseUrl + ALL_ROLES_URI_TEMPLATE, RolesBean.class);
    }

    @Override
    public ApplicationsBean applications() throws RestClientException {
        return restApi.get(baseUrl + ALL_APPLICATIONS_URI_TEMPLATE, ApplicationsBean.class);
    }

    @Override
    public UsersBean users() throws RestClientException {
        return restApi.get(baseUrl + ALL_USERS_URI_TEMPLATE, UsersBean.class);
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
        return restApi.get(baseUrl + ALL_GROUPS_URI_TEMPLATE, GroupsBean.class);
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
