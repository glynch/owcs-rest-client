package io.github.glynch.owcs.rest.client.support;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.ASSETTYPE_URI_TEMPLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TestUriBuilder {

    @Test
    void testUriBuilder() {
        UriBuilder uriBuilder = new DefaultUriBuilder(ASSETTYPE_URI_TEMPLATE);
        URI uri = uriBuilder.build(Map.of("type", "AVIArticle"));
        assertEquals("/REST/types/AVIArticle", uri.toString());
    }

}
