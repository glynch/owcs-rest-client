package io.github.glynch.owcs.rest.client.v1;

import java.net.URI;
import java.util.Map;

import io.github.glynch.owcs.rest.client.api.RestApi;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.search.AssetQuery;
import io.github.glynch.owcs.rest.client.v1.search.CollectionQuery;
import io.github.glynch.owcs.rest.client.v1.search.RecommendationQuery;
import io.github.glynch.owcs.rest.client.v1.search.V1SearchQuery;
import io.github.glynch.owcs.rest.support.Sites;
import io.github.glynch.owcs.rest.support.Types;
import io.github.glynch.owcs.rest.support.Versions;
import oracle.fatwire.rest.standard.beans.CollectionResourceMap;
import oracle.fatwire.rest.v1.maps.ResourceDescriptionMap;

public interface V1RestClient {

    String baseUrl();

    RestApi restApi();

    String RESOURCES_URI_TEMPLATE = "/REST/resources";
    String RESOURCES_VERSION_URI_TEMPLATE = "/REST/resources/{version}";
    String SEARCH_URI_TEMPLATE = "/REST/resources/v1/search/assets";
    String SEARCH_METADATA_CATALOG_URI_TEMPLATE = "/REST/resources/v1/metadata-catalog/search/assets";

    Map<String, ?> resources() throws RestClientException;

    Map<String, ?> resource(Versions version) throws RestClientException;

    ResourceResources resources(Versions version);

    SiteResources sites(Sites site);

    CollectionResourceMap search(V1SearchQuery query) throws RestClientException;

    CollectionResourceMap search(String query) throws RestClientException;

    SearchResources search() throws RestClientException;

    interface ResourceResources {

        String RESOURCES_VERSION_METADATA_CATALOG_URI_TEMPLATE = "/REST/resources/{version}/metadata-catalog";

        Map<String, ?> metaDataCatalog() throws RestClientException;
    }

    interface SiteResources {

        SiteTypeResources types(Types type);

        SiteCollectionResources contentQuery();

        SiteCollectionResources advCols();

        SiteRecommendationResources recommendation(String name);

        CollectionResourceMap search(V1SearchQuery query) throws RestClientException;

        CollectionResourceMap search(String query) throws RestClientException;

        SiteSearchResources search() throws RestClientException;
    }

    interface SiteTypeResources {

        String SITE_TYPE_ASSET_URI_TEMPLATE = "/REST/resources/v1/aggregates/{site}/{type}/{id}";
        String SITE_TYPE_ASSET_METADATA_CATALOG_URI_TEMPLATE = "/REST/resources/v1/metadata-catalog/aggregates/{site}/{type}/{id}";

        CollectionResourceMap id(long id, AssetQuery query) throws RestClientException;

        CollectionResourceMap id(long id) throws RestClientException;

        ResourceDescriptionMap metaDataCatalog(long id) throws RestClientException;

    }

    interface SiteCollectionResources {

        String SITE_COLLECTION_ITEMS_URI_TEMPLATE = "/REST/resources/v1/aggregates/{site}/{type}/{id}/items";
        String SITE_COLLECTION_ITEMS_METADATA_CATALOG_URI_TEMPLATE = "/REST/resources/v1/metadata-catalog/aggregates/{site}/{type}/{id}/items";

        CollectionResourceMap items(long id, CollectionQuery query) throws RestClientException;

        CollectionResourceMap items(long id) throws RestClientException;

        ResourceDescriptionMap metaDataCatalog(long id) throws RestClientException;

        URI options(long id) throws RestClientException;

    }

    interface SiteRecommendationResources {
        String SITE_RECOMMENDATION_ITEMS_URI_TEMPLATE = "/REST/resources/v1/aggregates/{site}/engage/recommendation/{name}/items";
        String SITE_RECOMMENDATION_ITEMS_METADATA_CATALOG_URI_TEMPLATE = "/REST/resources/v1/metadata-catalog/aggregates/{site}/engage/recommendation/{name}/items";

        CollectionResourceMap items(RecommendationQuery query) throws RestClientException;

        CollectionResourceMap items() throws RestClientException;

        URI options() throws RestClientException;

        ResourceDescriptionMap metaDataCatalog() throws RestClientException;

    }

    interface TypeResources {

        CollectionResourceMap search() throws RestClientException;
    }

    interface SearchResources {

        String SEARCH_METADATA_CATALOG_URI_TEMPLATE = "/REST/resources/v1/metadata-catalog/search/assets";

        URI options() throws RestClientException;

        Map<String, ?> metaDataCatalog() throws RestClientException;

    }

    interface SiteSearchResources {
        URI options() throws RestClientException;

        Map<String, ?> metaDataCatalog() throws RestClientException;
    }

}
