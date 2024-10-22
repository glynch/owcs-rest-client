package io.github.glynch.owcs.rest.support;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TestUriBuilder {

    @Test
    void testFromMapSingleVariable() {
        UriBuilder uriBuilder = new DefaultUriBuilder("/REST/types/{type}");
        URI uri = uriBuilder.build(Map.of("type", "AVIArticle"));
        assertEquals("/REST/types/AVIArticle", uri.toString());
    }

    @Test
    void testFromMapMultipleVariables() {
        UriBuilder uriBuilder = new DefaultUriBuilder("/REST/sites/{site}/types/{type}/assets/{id}");
        URI uri = uriBuilder.build(Map.of("site", "avisports", "type", "AVIArticle", "id", 1395380847207L));
        assertEquals("/REST/sites/avisports/types/AVIArticle/assets/1395380847207", uri.toString());
    }

    @Test
    void testFromVarargsSingleVariable() {
        UriBuilder uriBuilder = new DefaultUriBuilder("/REST/types/{type}");
        URI uri = uriBuilder.build("AVIArticle");
        assertEquals("/REST/types/AVIArticle", uri.toString());
    }

    @Test
    void testFromVarargsMultipleVariables() {
        UriBuilder uriBuilder = new DefaultUriBuilder("/REST/sites/{site}/types/{type}/assets/{id}");
        URI uri = uriBuilder.build("avisports", "AVIArticle", 1395380847207L);
        assertEquals("/REST/sites/avisports/types/AVIArticle/assets/1395380847207", uri.toString());
    }

    @Test
    void testEncodedVariables() {
        UriBuilder uriBuilder = new DefaultUriBuilder(
                "/REST/resources/v1/aggregates/{site}/engage/recommendation/{name}/items");
        URI uri = uriBuilder.encode().build(Map.of("site", "avisports", "name", "Running Recommendation"));
        assertEquals("/REST/resources/v1/aggregates/avisports/engage/recommendation/Running%20Recommendation/items",
                uri.toString());
    }

}
