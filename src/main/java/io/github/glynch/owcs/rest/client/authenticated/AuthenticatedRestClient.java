package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import javax.cache.Cache;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.AssociationsBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.EnabledTypesBean;
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
import io.github.glynch.owcs.rest.client.sso.TokenProvider;
import io.github.glynch.owcs.rest.client.support.Indexes;
import io.github.glynch.owcs.rest.client.support.Roles;
import io.github.glynch.owcs.rest.client.support.Sites;
import io.github.glynch.owcs.rest.client.support.Types;

public interface AuthenticatedRestClient {

    AuthenticatedRestApi restApi();

    String baseUrl();

    interface Builder {

        /*
         * Sets the token provider for the client for authenticated resources.
         */
        Builder tokenProvider(TokenProvider tokenProvider);

        /**
         * Sets the caching token provider for the client for authenticated resources.
         *
         * @param cache
         * @return this
         */
        Builder cachingTokenProvider(Cache<String, String> cache);

        /**
         * Sets the caching token provider for the client for authenticated resources.
         * This uses the default token cache.
         *
         * @return this
         */
        Builder cachingTokenProvider();

        AuthenticatedRestClient build();
    }

    String RESOURCES_VERSION_URI_TEMPLATE = "/REST/resources/{version}";

    String TYPES_URI_TEMPLATE = "/REST/types";
    String TYPE_URI_TEMPLATE = "/REST/types/{type}";
    String INDEXES_URI_TEMPLATE = "/REST/indexes";
    String INDEX_URI_TEMPLATE = "/REST/indexes/{index}";
    String SITES_URI_TEMPLATE = "/REST/sites";
    String SITE_URI_TEMPLATE = "/REST/sites/{site}";
    String ROLES_URI_TEMPLATE = "/REST/roles";
    String ROLE_URI_TEMPLATE = "/REST/roles/{role}";
    String APPLICATIONS_URI_TEMPLATE = "/REST/applications";
    String APPLICATION_URI_TEMPLATE = "/REST/applications/{id}";
    String USERS_URI_TEMPLATE = "/REST/users";
    String USER_URI_TEMPLATES = "/REST/users/{user}";
    String USER_LOCALES_URI_TEMPLATE = "/REST/userlocales";
    String USER_DEF_URI_TEMPLATE = "/REST/userdef";
    String ACLS_URI_TEMPLATE = "/REST/acls";
    String GROUPS_URI_TEMPLATE = "/REST/groups";
    String GROUP_URI_TEMPLATE = "/REST/groups/{group}";
    String TIMEZONE_URI_TEMPLATE = "/REST/timezone";
    String CURRENT_DEVICE_URI_TEMPLATE = "/REST/currentdevice";

    Map<String, ?> resources(String version);

    AssetTypesBean types() throws RestClientException;

    TypeResources types(Types type) throws RestClientException;

    AssetTypeBean type(Types type) throws RestClientException;

    IndexConfigsBean indexes() throws RestClientException;

    IndexConfigBean index(Indexes index) throws RestClientException;

    SitesBean sites() throws RestClientException;

    SiteBean site(Sites site) throws RestClientException;

    SiteResources sites(Sites site) throws RestClientException;

    RolesBean roles() throws RestClientException;

    RoleBean role(Roles role) throws RestClientException;

    ApplicationsBean applications() throws RestClientException;

    ApplicationBean application(long id) throws RestClientException;

    UsersBean users() throws RestClientException;

    UserBean user(String user) throws RestClientException;

    UserLocalesBean userLocales() throws RestClientException;

    UserDefBean userDef() throws RestClientException;

    AclsBean acls() throws RestClientException;

    GroupsBean groups() throws RestClientException;

    GroupBean group(String group) throws RestClientException;

    TimezoneBean timeZone() throws RestClientException;

    DeviceBean currentDevice() throws RestClientException;

    interface TypeResources {

        String TYPE_SUBTYPES_URI_TEMPLATE = "/REST/types/{type}/subtypes";
        String TYPE_SUBTYPE_URI_TEMPLATE = "/REST/types/{type}/subtypes/{subtype}";

        AssetTypeBean put(AssetTypeBean assetType) throws RestClientException;

        void delete() throws RestClientException;

        AssetTypesBean subtypes() throws RestClientException;

        AssetTypeBean subtype(String subtype) throws RestClientException;

    }

    interface SiteResources {

        String SITE_TYPES_URI_TEMPLATE = "/REST/sites/{site}/types";

        EnabledTypesBean types() throws RestClientException;

        AssetTypeBean type(Types type) throws RestClientException;

        SiteTypeResources types(Types type) throws RestClientException;

    }

    interface SiteTypeResources {

        SiteTypeAssetResources assets() throws RestClientException;
    }

    interface SiteTypeAssetResources {

        String SITE_TYPE_ASSET_URI_TEMPLATE = "/REST/sites/{site}/types/{type}/assets/{id}";
        String SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE = "/REST/sites/{site}/types/{type}/assets/{id}/associations";

        AssetBean asset(long id) throws RestClientException;

        AssetBean put(AssetBean assetBean) throws RestClientException;

        AssetBean post(AssetBean assetBean) throws RestClientException;

        void delete(long id) throws RestClientException;

        AssociationsBean associations(long id) throws RestClientException;

    }

}
