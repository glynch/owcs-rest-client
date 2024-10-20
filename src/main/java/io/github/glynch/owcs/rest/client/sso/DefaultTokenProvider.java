package io.github.glynch.owcs.rest.client.sso;

import java.util.Objects;

public class DefaultTokenProvider implements TokenProvider {

    private final TicketProvider ticketProvider;
    private final TicketEncryptor ticketEncryptor;

    public DefaultTokenProvider(TicketProvider ticketProvider, TicketEncryptor ticketEncryptor) {
        this.ticketProvider = ticketProvider;
        this.ticketEncryptor = ticketEncryptor;
    }

    @Override
    public String getToken(String baseUrl, String username, String password) throws SSOException {
        Objects.requireNonNull(baseUrl, "baseUrl");
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(password, "password");
        return ticketEncryptor.encrypt(baseUrl, ticketProvider.getMultiTicket(baseUrl, username, password));
    }

}
