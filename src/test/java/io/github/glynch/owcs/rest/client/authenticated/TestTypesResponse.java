package io.github.glynch.owcs.rest.client.authenticated;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fatwire.rest.beans.AssetTypesBean;

import io.github.glynch.owcs.rest.client.RestClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class TestTypesResponse {

    static MockWebServer server;
    static AuthenticatedRestClient client;

    @BeforeAll
    static void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        client = RestClient.builder(server.url("/sites").toString())
                .trace()
                .authenticated("fwadmin", "xceladmin")
                .cachingTokenProvider()
                .build();
    }

    @Test
    void typesResponse() throws IOException {
        MockResponse multiTicketResponse = new MockResponse();
        multiTicketResponse.setBody("multi-ST-1-l5FXR0vjW1amNsULsH5K-cas-localhost-1");
        MockResponse encryptedTokenResponse = new MockResponse();
        String encryptedToken = Files.readString(Path.of("src/test/resources/encrypted_token.json"));
        encryptedTokenResponse.setBody(encryptedToken);
        MockResponse response = new MockResponse();
        String json = Files.readString(Path.of("src/test/resources/responses/types.json"));
        response.setBody(json);
        server.enqueue(multiTicketResponse);
        server.enqueue(encryptedTokenResponse);
        server.enqueue(response);

        AssetTypesBean types = client.types();
        assertAll(
                () -> assertEquals(58, types.getTypes().size()),
                () -> assertEquals("http://localhost:7003/sites/REST/types/AVIArticle",
                        types.getTypes().get(0).getHref()),
                () -> assertEquals("AVIArticle",
                        types.getTypes().get(0).getName()),
                () -> assertEquals(List.of("Article", "ArticleImage", "Image"),
                        types.getTypes().get(0).getSubtypes())

        );
    }

    @AfterAll
    static void tearDown() throws IOException {
        server.shutdown();
    }

}
