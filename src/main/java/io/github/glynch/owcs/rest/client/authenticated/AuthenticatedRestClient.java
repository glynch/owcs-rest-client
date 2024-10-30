package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import javax.cache.Cache;

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
import com.fatwire.rest.beans.SitesBean;
import com.fatwire.rest.beans.TimezoneBean;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UserDefBean;
import com.fatwire.rest.beans.UserLocalesBean;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.api.AuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.authenticated.search.AssetQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.LuceneSearchQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.RecommendationQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.SearchQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.SegmentsQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.VisitorHistoryQuery;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.sso.TokenProvider;
import io.github.glynch.owcs.rest.support.Associations;
import io.github.glynch.owcs.rest.support.Indexes;
import io.github.glynch.owcs.rest.support.Roles;
import io.github.glynch.owcs.rest.support.Sites;
import io.github.glynch.owcs.rest.support.Subtypes;
import io.github.glynch.owcs.rest.support.Types;
import io.github.glynch.owcs.rest.support.Users;
import io.github.glynch.owcs.rest.support.Versions;

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
    String SITES_URI_TEMPLATE = "/REST/sites";
    String SITE_URI_TEMPLATE = "/REST/sites/{site}";
    String INDEXES_URI_TEMPLATE = "/REST/indexes";
    String INDEX_URI_TEMPLATE = "/REST/indexes/{index}";
    String ROLES_URI_TEMPLATE = "/REST/roles";
    String ROLE_URI_TEMPLATE = "/REST/roles/{role}";
    String APPLICATIONS_URI_TEMPLATE = "/REST/applications";
    String APPLICATION_URI_TEMPLATE = "/REST/applications/{id}";
    String USERS_URI_TEMPLATE = "/REST/users";
    String USER_URI_TEMPLATE = "/REST/users/{user}";
    String USER_LOCALES_URI_TEMPLATE = "/REST/userlocales";
    String USER_DEF_URI_TEMPLATE = "/REST/userdef";
    String ACLS_URI_TEMPLATE = "/REST/acls";
    String GROUPS_URI_TEMPLATE = "/REST/groups";
    String GROUP_URI_TEMPLATE = "/REST/groups/{group}";
    String TIMEZONE_URI_TEMPLATE = "/REST/timezone";
    String CURRENT_DEVICE_URI_TEMPLATE = "/REST/currentdevice";
    String GLOBAL_SEARCH_URI_TEMPLATE = "/REST/search";

    /**
     * Get the resources for the given version.
     * 
     * @param version
     * @return the resources for the given version
     */
    Map<String, ?> resources(Versions version);

    AssetsBean search(LuceneSearchQuery query) throws RestClientException;

    AssetTypesBean types() throws RestClientException;

    TypeResources type(Types type);

    TypeResources type(String type);

    AssetTypeBean put(AssetTypeBean assetType) throws RestClientException;

    IndexConfigsBean indexes() throws RestClientException;

    IndexConfigBean index(Indexes index) throws RestClientException;

    IndexConfigBean index(String index) throws RestClientException;

    SitesBean sites() throws RestClientException;

    SiteResources site(Sites site);

    SiteResources site(String site);

    SiteBean put(SiteBean siteBean) throws RestClientException;

    SiteBean post(SiteBean siteBean) throws RestClientException;

    RolesBean roles() throws RestClientException;

    RoleBean role(Roles role) throws RestClientException;

    RoleBean role(String role) throws RestClientException;

    ApplicationsBean applications() throws RestClientException;

    ApplicationBean put(ApplicationBean application) throws RestClientException;

    ApplicationBean post(ApplicationBean application) throws RestClientException;

    ApplicationResources application(long id) throws RestClientException;

    UsersBean users() throws RestClientException;

    UserResources user(Users user);

    UserResources user(String user);

    UserBean put(UserBean user) throws RestClientException;

    UserBean post(UserBean user) throws RestClientException;

    UserLocalesBean userLocales() throws RestClientException;

    UserDefBean userDef() throws RestClientException;

    AclsBean acls() throws RestClientException;

    GroupsBean groups() throws RestClientException;

    GroupBean group(String group) throws RestClientException;

    TimezoneBean timeZone() throws RestClientException;

    DeviceBean currentDevice() throws RestClientException;

    ListKeyValuePairs visitorHistory(VisitorHistoryQuery query) throws RestClientException;

    interface TypeResources {

        String TYPE_SUBTYPES_URI_TEMPLATE = "/REST/types/{type}/subtypes";
        String TYPE_SUBTYPE_URI_TEMPLATE = "/REST/types/{type}/subtypes/{subtype}";

        AssetTypeBean get() throws RestClientException;

        void delete() throws RestClientException;

        AssetTypesBean subtypes() throws RestClientException;

        AssetTypeBean subtype(Subtypes subtype) throws RestClientException;

        AssetTypeBean subtype(String subtype) throws RestClientException;

        AssetTypesBean search(SearchQuery query) throws RestClientException;

    }

    interface SiteResources {

        String SITE_SEARCH_URI_TEMPLATE = "/REST/sites/{site}/search";
        String SITE_URI_TEMPLATE = "/REST/sites/{site}";
        String SITE_TYPES_URI_TEMPLATE = "/REST/sites/{site}/types";
        String SITE_NAVIGATION_URI_TEMPLATE = "/REST/sites/{site}/navigation";
        String SITE_USERS_URI_TEMPLATE = "/REST/sites/{site}/users";
        String SITE_USER_URI_TEMPLATE = "/REST/sites/{site}/users/{user}";
        String SITE_RECOMMENDATION_URI_TEMPLATE = "/REST/sites/{site}/engage/recommendation/{recommendation}";
        String SITE_SEGMENTS_URI_TEMPLATE = "/REST/sites/{site}/engage/segments";

        SiteBean get() throws RestClientException;

        Map<String, String> head() throws RestClientException;

        void delete() throws RestClientException;

        AssetBean put(AssetBean assetBean) throws RestClientException;

        AssetBean post(AssetBean assetBean) throws RestClientException;

        EnabledTypesBean types() throws RestClientException;

        SiteTypeResources type(Types type);

        SiteTypeResources type(String type);

        NavigationBean navigation() throws RestClientException;

        NavigationBean navigation(long id) throws RestClientException;

        SiteUserBean users() throws RestClientException;

        SiteUserBean user(Users user) throws RestClientException;

        SiteUserBean user(String user) throws RestClientException;

        AssetsBean recommendation(String recommendation, RecommendationQuery query) throws RestClientException;

        AssetsBean search(LuceneSearchQuery query) throws RestClientException;

        AssetsBean segments(SegmentsQuery query) throws RestClientException;

    }

    interface SiteTypeResources {
        String SITE_TYPE_SEARCH_URI_TEMPLATE = "/REST/sites/{site}/types/{type}/search";
        String SITE_TYPE_URI_TEMPLATE = "/REST/sites/{site}/types/{type}";
        String SITE_TYPE_ASSET_URI_TEMPLATE = "/REST/sites/{site}/types/{type}/assets/{id}";

        SiteTypeAssetResources id(long id) throws RestClientException;

        AssetsBean search(AssetQuery query) throws RestClientException;
    }

    interface SiteTypeAssetResources {

        String SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE = "/REST/sites/{site}/types/{type}/assets/{id}/associations";
        String SITE_TYPE_ASSET_ASSOCIATION_URI_TEMPLATE = "/REST/sites/{site}/types/{type}/assets/{id}/associations/{association}";

        AssetBean get() throws RestClientException;

        void delete() throws RestClientException;

        Map<String, String> head() throws RestClientException;

        AssociationsBean associations() throws RestClientException;

        AssociationBean association(Associations association) throws RestClientException;

        AssociationBean association(String association) throws RestClientException;

    }

    interface ApplicationResources {

        void delete() throws RestClientException;

        ApplicationBean get() throws RestClientException;

    }

    interface UserResources {

        UserBean get() throws RestClientException;

        void delete() throws RestClientException;

        Map<String, String> head() throws RestClientException;

    }

}
