package io.github.glynch.owcs.rest.client.sso;

/*
 * This interface provides a contract for classes that provide tickets for CAS Single Sign-On (SSO) authentication.
 */
public interface TicketProvider {

    /*
     * This method returns a ticket for the given service.
     * 
     * Can be used only during some limited period of time for one resource and only
     * once
     * 
     * @param baseUrl The base URL of the CAS server.
     * 
     * @param service The service for which the ticket is requested.
     * 
     * @param username The username of the user requesting the ticket.
     * 
     * @param password The password of the user requesting the ticket.
     * 
     * @return The ticket for the given service.
     * 
     * @throws SSOException If an error occurs while retrieving the ticket.
     */
    String getTicket(String baseUrl, String service, String username, String password) throws SSOException;

    /*
     * This method returns a multi-ticket for the given username.
     * 
     * Can be used only during some limited period, multiple times for any resource.
     * 
     * @param baseUrl The base URL of the CAS server.
     * 
     * @param username The username of the user requesting the ticket.
     * 
     * @param password The password of the user requesting the ticket.
     * 
     * @return The multi-ticket for the given username.
     * 
     * @throws SSOException If an error occurs while retrieving the multi-ticket.
     */
    String getMultiTicket(String baseUrl, String username, String password) throws SSOException;
}
