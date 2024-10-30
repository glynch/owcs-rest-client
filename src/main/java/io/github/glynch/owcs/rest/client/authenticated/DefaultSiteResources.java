package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;
import java.util.Objects;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SiteUserBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.authenticated.search.LuceneSearchQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.RecommendationQuery;
import io.github.glynch.owcs.rest.client.authenticated.search.SegmentsQuery;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.support.Types;
import io.github.glynch.owcs.rest.support.Users;
import io.github.glynch.owcs.rest.utils.AssetIds;

public class DefaultSiteResources implements SiteResources {

    private final AuthenticatedRestClient client;
    private final String site;

    DefaultSiteResources(AuthenticatedRestClient client, String site) {
        this.client = client;
        this.site = site;
    }

    @Override
    public SiteBean get() throws RestClientException {
        Objects.requireNonNull(site, "site cannot be null");
        return client.restApi().get(client.baseUrl() + SITE_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)),
                SiteBean.class);
    }

    @Override
    public Map<String, String> head() throws RestClientException {
        return client.restApi().head(client.baseUrl() + SITE_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)));
    }

    @Override
    public void delete() throws RestClientException {
        client.restApi().delete(client.baseUrl() + SITE_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)));
    }

    @Override
    public EnabledTypesBean types() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPES_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)),
                EnabledTypesBean.class);
    }

    @Override
    public SiteTypeResources type(Types type) throws RestClientException {
        Objects.requireNonNull(type, "type cannot be null");
        return new DefaultSiteTypeResources(client, site, type.getName());
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
    public SiteUserBean user(Users user) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_USER_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "user", user.getName())),
                SiteUserBean.class);
    }

    @Override
    public AssetsBean recommendation(String recommendation, RecommendationQuery query) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_RECOMMENDATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "recommendation", recommendation)),
                AssetsBean.class);
    }

    @Override
    public AssetsBean search(LuceneSearchQuery query) throws RestClientException {
        Objects.requireNonNull(query, "query cannot be null");
        return client.restApi().get(client.baseUrl() + SITE_SEARCH_URI_TEMPLATE,
                builder -> builder.queryParams(query.queryParams()).build(Map.of("site", site)),
                AssetsBean.class);
    }

    @Override
    public AssetsBean segments(SegmentsQuery query) throws RestClientException {
        throw new UnsupportedOperationException("Unimplemented method 'segments'");
    }

    @Override
    public AssetBean put(AssetBean assetBean) throws RestClientException {
        Objects.requireNonNull(assetBean, "assetBean cannot be null");
        AssetId assetId = AssetIds.of(assetBean.getId());
        return client.restApi().put(client.baseUrl() + SiteTypeResources.SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", assetId.getType(), "id", 0L)),
                assetBean,
                AssetBean.class);
    }

    @Override
    public AssetBean post(AssetBean assetBean) throws RestClientException {
        Objects.requireNonNull(assetBean, "assetBean cannot be null");
        AssetId assetId = AssetIds.of(assetBean.getId());
        return client.restApi().post(client.baseUrl() + SiteTypeResources.SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", assetId.getType(), "id", assetId.getId())),
                assetBean,
                AssetBean.class);
    }

}
