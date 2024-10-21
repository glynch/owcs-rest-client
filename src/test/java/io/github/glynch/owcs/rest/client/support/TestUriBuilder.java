package io.github.glynch.owcs.rest.client.support;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.TYPE_URI_TEMPLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.glynch.owcs.rest.support.UriBuilder;

public class TestUriBuilder {

    @Test
    void testUriBuilder() {
        UriBuilder uriBuilder = new DefaultUriBuilder(TYPE_URI_TEMPLATE);
        URI uri = uriBuilder.build(Map.of("type", "AVIArticle"));
        assertEquals("/REST/types/AVIArticle", uri.toString());
    }

}
