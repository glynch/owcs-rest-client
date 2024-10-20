package io.github.glynch.owcs.rest.client.sso;

public interface TokenProvider {

    String X_CSRF_TOKEN = "X-CSRF-TOKEN";

    String getToken(String baseUrl, String username, String password) throws SSOException;

}
