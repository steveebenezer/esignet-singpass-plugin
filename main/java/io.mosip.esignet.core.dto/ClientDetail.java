package io.mosip.esignet.core.dto;

public class ClientDetail {
    private String clientId;
    private String clientName;
    // Add other fields as required

    // Getters and setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}