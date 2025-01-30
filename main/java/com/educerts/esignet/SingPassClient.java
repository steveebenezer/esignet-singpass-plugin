package com.example.esignet.singpass;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SingPassClient {
    private static final String SINGPASS_AUTH_URL = "https://api.singpass.gov.sg/auth";
    private static final String SINGPASS_USERINFO_URL = "https://api.singpass.gov.sg/userinfo";
    private static final String SINGPASS_OTP_URL = "https://api.singpass.gov.sg/otp";

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public SingPassClient() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Authenticate with SingPass and retrieve an access token.
     *
     * @param username The user's SingPass username.
     * @param password The user's SingPass password.
     * @return SingPassAuthResponse containing the access token and user ID.
     * @throws IOException If the API call fails.
     */
    public SingPassAuthResponse authenticate(String username, String password) throws IOException {
        HttpPost request = new HttpPost(SINGPASS_AUTH_URL);
        request.setHeader("Content-Type", "application/json");

        String json = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        request.setEntity(new StringEntity(json));

        return httpClient.execute(request, response -> {
            return objectMapper.readValue(response.getEntity().getContent(), SingPassAuthResponse.class);
        });
    }

    /**
     * Retrieve user info from SingPass using the access token.
     *
     * @param accessToken The access token obtained during authentication.
     * @return SingPassUserInfo containing the user's details.
     * @throws IOException If the API call fails.
     */
    public SingPassUserInfo getUserInfo(String accessToken) throws IOException {
        HttpPost request = new HttpPost(SINGPASS_USERINFO_URL);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Bearer " + accessToken);

        return httpClient.execute(request, response -> {
            return objectMapper.readValue(response.getEntity().getContent(), SingPassUserInfo.class);
        });
    }

    /**
     * Send an OTP to the user's registered channel (e.g., SMS, email).
     *
     * @param individualId The user's unique identifier.
     * @param channel      The channel to send the OTP (e.g., "SMS", "EMAIL").
     * @return SingPassOtpResponse containing the status and OTP reference ID.
     * @throws IOException If the API call fails.
     */
    public SingPassOtpResponse sendOtp(String individualId, String channel) throws IOException {
        HttpPost request = new HttpPost(SINGPASS_OTP_URL);
        request.setHeader("Content-Type", "application/json");

        String json = String.format("{\"individualId\": \"%s\", \"channel\": \"%s\"}", individualId, channel);
        request.setEntity(new StringEntity(json));

        return httpClient.execute(request, response -> {
            return objectMapper.readValue(response.getEntity().getContent(), SingPassOtpResponse.class);
        });
    }
}