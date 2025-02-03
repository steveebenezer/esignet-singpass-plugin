package io.mosip.esignet.services.spi;

import io.mosip.esignet.core.dto.ClientDetail;
import io.mosip.esignet.core.exception.EsignetException;

public interface ClientManagementService {

    /**
     * Registers a new client with the provided client details.
     *
     * @param clientDetail The client details to register.
     * @throws EsignetException If the client registration fails.
     */
    void registerClient(ClientDetail clientDetail) throws EsignetException;

    /**
     * Updates an existing client's details.
     *
     * @param clientId      The ID of the client to update.
     * @param clientDetail  The updated client details.
     * @throws EsignetException If the client update fails.
     */
    void updateClient(String clientId, ClientDetail clientDetail) throws EsignetException;

    /**
     * Retrieves the details of a client by their ID.
     *
     * @param clientId The ID of the client to retrieve.
     * @return The client details.
     * @throws EsignetException If the client retrieval fails.
     */
    ClientDetail getClient(String clientId) throws EsignetException;

    /**
     * Deletes a client by their ID.
     *
     * @param clientId The ID of the client to delete.
     * @throws EsignetException If the client deletion fails.
     */
    void deleteClient(String clientId) throws EsignetException;
}