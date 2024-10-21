package io.github.glynch.owcs.rest.client.sso;

import okhttp3.OkHttpClient;

public interface TokenProvider {

    String X_CSRF_TOKEN = "X-CSRF-TOKEN";

    String getToken(String baseUrl, String username, String password) throws SSOException;

    static TokenProvider create() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return new DefaultTokenProvider(new DefaultTicketProvider(client), new DefaultTicketEncryptor(client));
    }

}
