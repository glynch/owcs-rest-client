package io.github.glynch.owcs.rest.client.sso;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultTicketEncryptor implements TicketEncryptor {

    private static final String PRAGMA_HEADER = "Pragma";
    private static final String ENCRYPTED_TOKEN = "encryptedtoken";
    private static final String MULTITICKET = "multiticket";
    private static final String TICKET_ENCRYPTOR_PATH = "/wem/service/encrypttoken";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final MediaType APPLICATION_JSON = MediaType.parse("application/json");
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private final OkHttpClient client;

    public DefaultTicketEncryptor(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public String encrypt(String baseUrl, String ticket) throws SSOException {
        Objects.requireNonNull(baseUrl, "baseUrl is required");
        Objects.requireNonNull(ticket, "ticket is required");
        FormBody formBody = new FormBody.Builder()
                .add(MULTITICKET, ticket).build();

        Request request = new Request.Builder()
                .url(baseUrl + TICKET_ENCRYPTOR_PATH)
                .header(PRAGMA_HEADER, "auth-redirect=false")
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON.toString())
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        String encryptedTicket = null;
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new SSOException(
                        String.format("Failed to encrypt ticket: %d:", ticket, response.code()));
            }
            JsonNode jsonObject = objectMapper.readTree(response.body().string());
            if (jsonObject != null) {
                JsonNode encryptedTokenElement = jsonObject.get(ENCRYPTED_TOKEN);
                if (encryptedTokenElement != null) {
                    encryptedTicket = encryptedTokenElement.asText();
                }
            } else {
                throw new SSOException("Error parsing response from ticket encryptor");
            }
        } catch (IOException e) {
            throw new SSOException(String.format("Error encrypting ticket %s", ticket), e);
        }
        return encryptedTicket;
    }

}
