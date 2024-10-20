package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import javax.cache.Cache;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.AssetTypeBean;
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

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.sso.TokenProvider;

public interface AuthenticatedRestClient {

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

    Map<String, ?> resources(String version);

    String ALL_ASSETTYPES_URI_TEMPLATE = "/REST/types";
    String ASSETTYPE_URI_TEMPLATE = "/REST/types/{type}";
    String INDEXES_URI_TEMPLATE = "/REST/indexes";
    String ALL_SITES_URI_TEMPLATE = "/REST/sites";
    String ALL_ROLES_URI_TEMPLATE = "/REST/roles";
    String ALL_APPLICATIONS_URI_TEMPLATE = "/REST/applications";
    String ALL_USERS_URI_TEMPLATE = "/REST/users";
    String USER_LOCALES_URI_TEMPLATE = "/REST/userlocales";
    String USER_DEF_URI_TEMPLATE = "/REST/userdef";
    String ACLS_URI_TEMPLATE = "/REST/acls";
    String ALL_GROUPS_URI_TEMPLATE = "/REST/groups";
    String GROUP_URI_TEMPLATE = "/REST/groups/{group}";
    String TIMEZONE_URI_TEMPLATE = "/REST/timezone";
    String CURRENT_DEVICE_URI_TEMPLATE = "/REST/currentdevice";

    AssetTypesBean types() throws RestClientException;

    AssetTypeResources types(String type) throws RestClientException;

    IndexConfigsBean indexes() throws RestClientException;

    SitesBean sites() throws RestClientException;

    RolesBean roles() throws RestClientException;

    ApplicationsBean applications() throws RestClientException;

    UsersBean users() throws RestClientException;

    UserLocalesBean userLocales() throws RestClientException;

    UserDefBean userDef() throws RestClientException;

    AclsBean acls() throws RestClientException;

    GroupsBean groups() throws RestClientException;

    GroupBean group(String group) throws RestClientException;

    TimezoneBean timeZone() throws RestClientException;

    DeviceBean currentDevice() throws RestClientException;

    interface AssetTypeResources {

        String ALL_SUBTYPES_URI_TEMPLATE = "/REST/types/{type}/subtypes";
        String SUBTYPE_URI_TEMPLATE = "/REST/types/{type}/subtypes/{subtype}";

        AssetTypeBean get() throws RestClientException;

        AssetTypeBean put(AssetTypeBean assetType) throws RestClientException;

        void delete() throws RestClientException;

        AssetTypesBean subtypes() throws RestClientException;

        AssetTypeBean subtypes(String subtype) throws RestClientException;

    }

}
