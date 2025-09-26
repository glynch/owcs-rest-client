package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.AssociationBean;
import com.fatwire.rest.beans.AssociationsBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.GroupBean;
import com.fatwire.rest.beans.GroupsBean;
import com.fatwire.rest.beans.IndexConfigBean;
import com.fatwire.rest.beans.IndexConfigsBean;
import com.fatwire.rest.beans.ListKeyValuePairs;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.rest.beans.RoleBean;
import com.fatwire.rest.beans.RolesBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SiteUserBean;
import com.fatwire.rest.beans.SiteUsersBean;
import com.fatwire.rest.beans.SitesBean;
import com.fatwire.rest.beans.TimezoneBean;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UserDefBean;
import com.fatwire.rest.beans.UserLocalesBean;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.search.AssetSearchQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.LuceneAssetSearchQuery;
import io.github.glynch.owcs.sso.TokenProvider;

public interface AuthenticatedRestClient {

    <T> T get(String url, Class<T> responseType, Object... uriVariables) throws RestClientException;

    <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

    <T> T put(String url, T body, Class<T> responseType, Object... uriVariables) throws RestClientException;

    <T> T put(String url, T body, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

    <T> T post(String url, T body, Class<T> responseType, Object... uriVariables) throws RestClientException;

    <T> T post(String url, T body, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

    void delete(String url, Object... uriVariables) throws RestClientException;

    void delete(String url, Map<String, ?> uriVariables) throws RestClientException;

    <T> T head(String url, Class<T> responseType, Object... uriVariables) throws RestClientException;

    <T> T head(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

    String getBaseUrl();

    String getRestUrl();

    String getUsername();

    String getPassword();

    static Builder builder(String baseUrl, String username, String password) {
        Assert.hasText(baseUrl, "baseUrl cannot be empty or null");
        Assert.hasText(username, "username cannot be empty or null");
        Assert.hasText(password, "password cannot be empty or null");
        return new DefaultAuthenticatedRestClientBuilder(baseUrl, username, password);
    }

    interface Builder {

        /**
         * Sets the connect timeout for the REST client in milliseconds.
         * 
         * @param connectTimeOut
         * @return this
         */
        Builder connectTimeOut(int connectTimeOut);

        /**
         * Sets the read timeout for the REST client in milliseconds.
         * 
         * @param readTimeOut
         * @return this
         */
        Builder readTimeOut(int readTimeOut);

        /**
         * Sets the token provider for the client.
         * 
         * @param tokenProvider
         * 
         * @return this
         */
        Builder tokenProvider(TokenProvider tokenProvider);

        /**
         * Sets the default caching token provider for the client.
         * 
         * @see {@link io.github.glynch.owcs.sso.cache.CachingTokenProvider}
         *
         * @return this
         */
        Builder cachingTokenProvider();

        AuthenticatedRestClient build();
    }

    String TIMEZONES_URI_TEMPLATE = "/timezone";

    /**
     * Get the server's time zone.
     * 
     * @return the time zone bean
     * @throws RestClientException
     */
    TimezoneBean timezone() throws RestClientException;

    String CURRENT_DEVICE_URI_TEMPLATE = "/currentdevice";

    DeviceBean currentDevice() throws RestClientException;

    String ACLS_URI_TEMPLATE = "/acls";

    /**
     * List of all ACLs in the WebCenter Sites.
     * 
     * @return
     * @throws RestClientException
     */
    AclsBean acls() throws RestClientException;

    String USERS_URI_TEMPLATE = "/users";

    /**
     * List of all users in the WebCenter Sites.
     * 
     * @return the users bean
     * @throws RestClientException
     */
    UsersBean users() throws RestClientException;

    /**
     * Details of a specific user.
     * 
     * @param user
     * @return the user bean
     * @throws RestClientException
     */
    UserResources user(String user);

    String ROLES_URI_TEMPLATE = "/roles";

    /**
     * List of all roles in the WebCenter Sites.
     * 
     * @return the roles bean
     * @throws RestClientException
     */
    RolesBean roles() throws RestClientException;

    RoleResources role(String role);

    String GROUPS_URI_TEMPLATE = "/groups";

    /**
     * List of all groups in the WebCenter Sites.
     * 
     * @return the groups bean
     * @throws RestClientException
     */
    GroupsBean groups() throws RestClientException;

    String GROUP_URI_TEMPLATE = GROUPS_URI_TEMPLATE + "/{group}";

    /**
     * Details of a specific group.
     * 
     * @param group
     * @return the group bean
     * @throws RestClientException
     */
    GroupBean group(String group) throws RestClientException;

    String USER_LOCALES_URI_TEMPLATE = "/userlocales";

    UserLocalesBean userLocales() throws RestClientException;

    String USER_DEF_URI_TEMPLATE = "/userdef";

    UserDefBean userDef() throws RestClientException;

    String INDEXES_URI_TEMPLATE = "/indexes";

    IndexConfigsBean indexes() throws RestClientException;

    IndexResources index(String index);

    String APPLICATIONS_URI_TEMPLATE = "/applications";

    ApplicationsBean applications() throws RestClientException;

    ApplicationResources application(long applicationId);

    String VISTORY_HISTORY_URI_TEMPLATE = "/visitor/visitorhistory";

    ListKeyValuePairs vistoryHistory(long visitorId, long historyDef, String... fields);

    String TYPES_URI_TEMPLATE = "/types";

    /**
     * List of all asset types in the WebCenter Sites.
     * 
     * @return the asset types bean
     * @throws RestClientException
     */
    AssetTypesBean types() throws RestClientException;

    TypeResources type(String type);

    String SEARCH_URI_TEMPLATE = "/search";

    AssetsBean search(LuceneAssetSearchQuery query) throws RestClientException;

    AssetBean findByName(String name, String site, String type) throws RestClientException;

    /**
     * Resources for a specific asset type.
     */
    interface TypeResources {

        String TYPE_URI_TEMPLATE = TYPES_URI_TEMPLATE + "/{type}";
        String TYPE_SEARCH_URI_TEMPLATE = TYPE_URI_TEMPLATE + "/search";
        String TYPE_SUBTYPES_URI_TEMPLATE = TYPE_URI_TEMPLATE + "/subtypes";
        String TYPE_SUBTYPE_URI_TEMPLATE = TYPE_SUBTYPES_URI_TEMPLATE + "/{subtype}";

        /*
         * Reads the asset type.
         * 
         * @return the asset type bean
         */
        AssetTypeBean read() throws RestClientException;

        /*
         * Creates the asset type.
         * 
         * @param assetTypeBean the asset type bean to create
         * 
         * @return the created asset type bean
         */
        AssetTypeBean create(AssetTypeBean assetTypeBean) throws RestClientException;

        void delete() throws RestClientException;

        AssetTypesBean subtypes() throws RestClientException;

        AssetTypeBean subtype(String subtype);

        AssetsBean search(AssetSearchQuery query) throws RestClientException;

        AssetsBean search() throws RestClientException;
    }

    String SITES_URI_TEMPLATE = "/sites";

    SitesBean sites() throws RestClientException;

    SiteResources site(String site);

    interface SiteResources {

        String SITE_URI_TEMPLATE = SITES_URI_TEMPLATE + "/{site}";
        String SITE_SEARCH_URI_TEMPLATE = SITE_URI_TEMPLATE + "/search";
        String SITE_USERS_URI_TEMPLATE = SITE_URI_TEMPLATE + "/users";
        String SITE_USER_URI_TEMPLATE = SITE_USERS_URI_TEMPLATE + "/{user}";

        String SITE_TYPES_URI_TEMPLATE = SITE_URI_TEMPLATE + "/types";
        String SITE_NAVIGATION_URI_TEMPLATE = SITE_URI_TEMPLATE + "/navigation";
        String SITE_NAVIGATION_PAGEID_URI_TEMPLATE = SITE_NAVIGATION_URI_TEMPLATE + "/{pageId}";

        SiteBean read() throws RestClientException;

        SiteBean create(SiteBean siteBean) throws RestClientException;

        SiteBean update(SiteBean siteBean) throws RestClientException;

        void delete() throws RestClientException;

        SiteBean head() throws RestClientException;

        // TODO: Fix these navigation methods to make it harder to pass in incorrect
        // arguments
        NavigationBean navigation() throws RestClientException;

        NavigationBean navigation(String depth) throws RestClientException;

        NavigationBean navigation(String depth, String code) throws RestClientException;

        NavigationBean navigation(long pageId) throws RestClientException;

        NavigationBean navigation(long pageId, String depth) throws RestClientException;

        EnabledTypesBean types() throws RestClientException;

        SiteTypeResources type(String type);

        SiteUsersBean users() throws RestClientException;

        SiteUserBean user(String user) throws RestClientException;

        AssetsBean search() throws RestClientException;

        AssetsBean search(LuceneAssetSearchQuery query) throws RestClientException;

    }

    interface SiteTypeResources {

        String SITE_TYPE_URI_TEMPLATE = SiteResources.SITE_URI_TEMPLATE + "/types/{type}";
        String SITE_TYPE_SEARCH_URI_TEMPLATE = SITE_TYPE_URI_TEMPLATE + "/search";

        AssetsBean search(AssetSearchQuery query) throws RestClientException;

        AssetsBean search() throws RestClientException;

        SiteTypeAssetResources asset(long id);

    }

    interface SiteTypeAssetResources {

        String SITE_TYPE_ASSET_URI_TEMPLATE = SiteTypeResources.SITE_TYPE_URI_TEMPLATE + "/assets/{id}";
        String SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE = SITE_TYPE_ASSET_URI_TEMPLATE + "/associations";
        String SITE_TYPE_ASSET_ASSOCIATION_URI_TEMPLATE = SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE + "/{assocName}";

        AssetBean read() throws RestClientException;

        AssetBean create(AssetBean assetBean) throws RestClientException;

        AssetBean update(AssetBean assetBean) throws RestClientException;

        AssociationsBean associations() throws RestClientException;

        AssociationBean association(String assocName) throws RestClientException;

        void delete() throws RestClientException;

    }

    interface RoleResources {

        String ROLE_URI_TEMPLATE = ROLES_URI_TEMPLATE + "/{role}";

        RoleBean read() throws RestClientException;

        RoleBean create(RoleBean roleBean) throws RestClientException;

        RoleBean update(RoleBean roleBean) throws RestClientException;

        void delete() throws RestClientException;

    }

    interface UserResources {

        String USER_URI_TEMPLATE = USERS_URI_TEMPLATE + "/{user}";

        UserBean read() throws RestClientException;

        UserBean create(UserBean userBean) throws RestClientException;

        UserBean update(UserBean userBean) throws RestClientException;

        void delete() throws RestClientException;

    }

    interface IndexResources {

        String INDEX_URI_TEMPLATE = "/indexes/{index}";

        IndexConfigBean read() throws RestClientException;

        IndexConfigBean create(IndexConfigBean indexConfigBean) throws RestClientException;

        IndexConfigBean update(IndexConfigBean indexConfigBean) throws RestClientException;

        void delete() throws RestClientException;
    }

    public interface ApplicationResources {

        String APPLICATION_URI_TEMPLATE = APPLICATIONS_URI_TEMPLATE + "/{applicationId}";

        ApplicationBean read() throws RestClientException;

        ApplicationBean create(ApplicationBean applicationBean) throws RestClientException;

        ApplicationBean update(ApplicationBean applicationBean) throws RestClientException;

        void delete() throws RestClientException;

    }

}
