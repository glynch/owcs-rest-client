package io.github.glynch.owcs.rest.client.sso;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultTicketProvider implements TicketProvider {

    private static final String USERNAME_PARAM = "username";
    private static final String PAGENAME_PARAM = "pagename";
    private static final String PASSWORD_PARAM = "password";
    private static final String ACTION_PARAM = "action";
    private static final String SERVICE_PARAM = "service";
    private static final String PAGENAME = "fatwire/wem/sso/processticket";
    private final OkHttpClient client;

    public DefaultTicketProvider(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public String getTicket(String baseUrl, String service, String username, String password) throws SSOException {
        return requestTicket(baseUrl, service, username, password);
    }

    @Override
    public String getMultiTicket(String baseUrl, String username, String password) throws SSOException {
        return requestTicket(baseUrl, "*", username, password);
    }

    private String requestTicket(String baseUrl, String service, String username, String password) throws SSOException {
        Objects.requireNonNull(baseUrl, "baseUrl is required");
        Objects.requireNonNull(service, "service is required");
        Objects.requireNonNull(username, "username is required");
        Objects.requireNonNull(password, "password is required");
        FormBody formBody = new FormBody.Builder()
                .add(PAGENAME_PARAM, PAGENAME)
                .add(USERNAME_PARAM, username)
                .add(PASSWORD_PARAM, password)
                .add(ACTION_PARAM, "ticket")
                .add(SERVICE_PARAM, service).build();

        Request request = new Request.Builder()
                .url(baseUrl + "/Satellite")
                .header("PRAGMA", "auth-redirect=false")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        String ticket = null;
        try (Response response = call.execute()) {
            String body = response.body().string().trim();
            if (!response.isSuccessful()) {
                throw new SSOException(
                        String.format("Failed to get ticket for service (%s): %d", service, response.code()));
            } else if (body.length() == 0) {
                // WebCenter Sites returns 200 OK with empty body when authentication fails
                throw new SSOException("Failed to get ticket: 0 byte response. Please check username and password.");
            }
            ticket = body;
        } catch (IOException e) {
            throw new SSOException("Failed to get ticket", e);
        }
        return ticket;

    }

}
