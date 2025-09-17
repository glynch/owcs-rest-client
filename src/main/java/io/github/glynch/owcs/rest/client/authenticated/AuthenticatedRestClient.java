package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.sso.TokenProvider;

public interface AuthenticatedRestClient {

    <T> T get(String url, Class<T> responseType, Object... uriVariables) throws RestClientException;

    <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

    <T> T put(String url, T body, Class<T> responseType, Object... uriVariables) throws RestClientException;

    <T> T put(String url, T body, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;

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

    /*
     * Gets the base URL for the REST client.
     * 
     * @return the base URL as a string
     */
    String getBaseUrl();

    /**
     * Gets the username used for authentication.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Gets the password used for authentication.
     *
     * @return the password
     */
    String getPassword();

    /*
     * Gets the token provider for the client for authenticated resources.
     */
    TokenProvider getTokenProvider();

    String TYPES_URI_TEMPLATE = "/REST/types";

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
         * @param assetTypeBean the asset type bean to update
         * 
         * @return the created asset type bean
         */
        AssetTypeBean create(AssetTypeBean assetTypeBean) throws RestClientException;

        AssetTypesBean subtypes() throws RestClientException;

        AssetTypeBean subtype(String subtype);
    }

    String SITES_URI_TEMPLATE = "/REST/sites";

    SitesBean sites() throws RestClientException;

}
