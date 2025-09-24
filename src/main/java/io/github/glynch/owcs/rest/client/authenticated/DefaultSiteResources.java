package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;

public class DefaultSiteResources implements SiteResources {

    private final AuthenticatedRestClient restClient;
    private final String site;

    public DefaultSiteResources(AuthenticatedRestClient restClient, String site) {
        this.restClient = restClient;
        this.site = site;

    }

    @Override
    public SiteBean read() throws RestClientException {
        return restClient.get(SITE_URI_TEMPLATE, SiteBean.class, site);
    }

    @Override
    public SiteBean create(SiteBean siteBean) throws RestClientException {
        return restClient.put(SITE_URI_TEMPLATE, siteBean, SiteBean.class, site);
    }

    @Override
    public SiteBean update(SiteBean siteBean) throws RestClientException {
        return restClient.post(SITE_URI_TEMPLATE, siteBean, SiteBean.class, site);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(SITE_URI_TEMPLATE, site);
    }

    @Override
    public SiteBean head() throws RestClientException {
        return restClient.head(SITE_URI_TEMPLATE, SiteBean.class, site);
    }

    @Override
    public NavigationBean navigation(NavigationSearch search) throws RestClientException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(SITE_NAVIGATION_URI_TEMPLATE);

        if (search != null) {
            if (search.getDepth() != null) {
                builder.queryParam("depth", search.getDepth());
            }
            if (search.getCode() != null) {
                builder.queryParam("code", search.getCode().name());
            }
        }

        return restClient.get(builder.build(false).toUriString(), NavigationBean.class, site);
    }

    @Override
    public NavigationBean navigation() throws RestClientException {
        return navigation(null);
    }

    @Override
    public NavigationBean navigation(long pageId) throws RestClientException {
        return navigation(pageId, null);
    }

    @Override
    public NavigationBean navigation(long pageId, String depth) throws RestClientException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(SITE_NAVIGATION_PAGEID_URI_TEMPLATE);

        if (depth != null) {
            builder.queryParam("depth", depth);
        }

        return restClient.get(builder.build(false).toUriString(), NavigationBean.class, site,
                pageId);
    }

    @Override
    public EnabledTypesBean types() throws RestClientException {
        return restClient.get(SITE_TYPES_URI_TEMPLATE, EnabledTypesBean.class, site);
    }

    @Override
    public SiteTypeResources type(String type) {
        Assert.hasText(type, "type cannot be empty or null");
        return new DefaultSiteTypeResources(restClient, site, type);
    }

    @Override
    public UsersBean users() throws RestClientException {
        return restClient.get(SITE_USERS_URI_TEMPLATE, UsersBean.class);
    }

    @Override
    public UserBean user(String user) throws RestClientException {
        Assert.hasText(user, "user cannot be empty or null");
        return restClient.get(SITE_USER_URI_TEMPLATE, UserBean.class, user);
    }

}
