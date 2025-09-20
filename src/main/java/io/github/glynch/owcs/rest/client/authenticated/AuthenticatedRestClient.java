package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.client.RestClientException;
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

        /*
         * Sets the token provider for the client.
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

    String TYPES_URI_TEMPLATE = "/types";

    AssetTypesBean types() throws RestClientException;

    TypeResources type(String type);

    interface TypeResources {

        String TYPE_URI_TEMPLATE = TYPES_URI_TEMPLATE + "/{type}";
        String TYPE_SUBTYPES_URI_TEMPLATE = TYPE_URI_TEMPLATE + "/subtypes";
        String TYPE_SUBTYPES_SUBTYPE_URI_TEMPLATE = TYPE_SUBTYPES_URI_TEMPLATE + "/{subtype}";

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

        AssetTypesBean subtypes() throws RestClientException;

        AssetTypeBean subtype(String subtype);
    }

    String SITES_URI_TEMPLATE = "/sites";

    SitesBean sites() throws RestClientException;

    SiteResources site(String site);

    interface SiteResources {

        String SITE_URI_TEMPLATE = SITES_URI_TEMPLATE + "/{site}";
        String SITE_NAVIGATION_URI_TEMPLATE = SITE_URI_TEMPLATE + "/navigation";
        String SITE_NAVIGATION_PAGEID_URI_TEMPLATE = SITE_NAVIGATION_URI_TEMPLATE + "/{pageId}";

        SiteBean read() throws RestClientException;

        SiteBean create(SiteBean siteBean) throws RestClientException;

        SiteBean update(SiteBean siteBean) throws RestClientException;

        void delete() throws RestClientException;

        SiteBean head() throws RestClientException;

        NavigationBean navigation(NavigationSearch search) throws RestClientException;

        NavigationBean navigation(long pageId) throws RestClientException;

        NavigationBean navigation(long pageId, String depth) throws RestClientException;

        NavigationBean navigation() throws RestClientException;

    }

}
