package io.github.glynch.owcs.rest.client;

import java.time.Duration;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.v1.V1RestClient;

public abstract class RestClient {

    public static Builder builder(String baseUrl) {
        return new DefaultRestClientBuilder(baseUrl);
    }

    public static AuthenticatedRestClient authenticated(String baseUrl, String username, String password) {
        return RestClient.builder(baseUrl).authenticated(username, password).cachingTokenProvider()
                .build();
    }

    public static V1RestClient v1(String baseUrl) {
        return RestClient.builder(baseUrl).build();
    }

    public interface Builder {
        /*
         * Allow redirects for the client
         */
        Builder allowRedirects();

        /*
         * Logs request and response lines.
         */
        Builder info();

        /*
         * Logs request and response lines and their respective headers and bodies (if
         * present).
         */
        Builder trace();

        /*
         * Sets the connect timeout for the client.
         */
        Builder connectTimeout(Duration duration);

        /*
         * Sets the read timeout for the client.
         */
        Builder readTimeout(Duration duration);

        AuthenticatedRestClient.Builder authenticated(String username, String password);

        V1RestClient build();
    }

}
