package io.github.glynch.owcs.rest.client.sso;

public interface TicketEncryptor {

    String encrypt(String baseUrl, String ticket) throws SSOException;
}
