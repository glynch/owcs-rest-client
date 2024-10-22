package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.rest.beans.SiteUserBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.support.Types;

public class DefaultSiteResources implements SiteResources {

    private final AuthenticatedRestClient client;
    private final String site;

    DefaultSiteResources(AuthenticatedRestClient client, String site) {
        this.client = client;
        this.site = site;
    }

    @Override
    public EnabledTypesBean types() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPES_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)),
                EnabledTypesBean.class);
    }

    @Override
    public AssetTypeBean type(Types type) throws RestClientException {
        return client.type(type);
    }

    @Override
    public SiteTypeResources types(Types type) throws RestClientException {
        return new DefaultSiteTypeResources(client, site, type.getName());
    }

    @Override
    public AssetsBean search(String query) throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public NavigationBean navigation() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_NAVIGATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)),
                NavigationBean.class);
    }

    @Override
    public NavigationBean navigation(long id) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_NAVIGATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "id", id)),
                NavigationBean.class);
    }

    @Override
    public SiteUserBean users() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_USERS_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)),
                SiteUserBean.class);
    }

    @Override
    public SiteUserBean user(String user) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_USER_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "user", user)),
                SiteUserBean.class);
    }

    @Override
    public AssetsBean recommendation(String recommendation) throws RestClientException {
        throw new UnsupportedOperationException("Unimplemented method 'recommendation'");
    }

}
