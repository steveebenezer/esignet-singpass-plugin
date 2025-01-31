package com.educerts.esignet.singpass;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SingPassAuthenticator implements Authenticator {
    private static final String SINGPASS_AUTH_URL = "https://api.singpass.gov.sg/auth";
    private static final String SINGPASS_USERINFO_URL = "https://api.singpass.gov.sg/userinfo";
    private static final String SINGPASS_OTP_URL = "https://api.singpass.gov.sg/otp";

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public SingPassAuthenticator() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public KycAuthResult doKycAuth(String relyingPartyId, String clientId, KycAuthDto kycAuthDto) throws KycAuthException {
        try {
            // Call SingPass authentication API
            SingPassAuthResponse authResponse = authenticateWithSingPass(kycAuthDto.getUsername(), kycAuthDto.getPassword());

            // Generate KYC token and PSUT
            String kycToken = generateKycToken(authResponse);
            String psut = generatePsut(authResponse);

            return new KycAuthResult(kycToken, psut);
        } catch (Exception e) {
            throw new KycAuthException("Failed to authenticate with SingPass", e);
        }
    }

    private SingPassAuthResponse authenticateWithSingPass(String username, String password) throws Exception {
        HttpPost request = new HttpPost(SINGPASS_AUTH_URL);
        request.setHeader("Content-Type", "application/json");

        String json = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        request.setEntity(new StringEntity(json));

        return httpClient.execute(request, response -> {
            return objectMapper.readValue(response.getEntity().getContent(), SingPassAuthResponse.class);
        });
    }

    @Override
    public KycAuthResult doKycAuth(String relyingPartyId, String clientId, KycAuthDto kycAuthDto) throws KycAuthException {
        try {
            // Call SingPass authentication API
            SingPassAuthResponse authResponse = singPassClient.authenticate(kycAuthDto.getUsername(), kycAuthDto.getPassword());

            // Generate KYC token and PSUT
            String kycToken = generateKycToken(authResponse);
            String psut = generatePsut(authResponse);

            return new KycAuthResult(kycToken, psut);
        } catch (Exception e) {
            throw new KycAuthException("Failed to authenticate with SingPass", e);
        }
    }

    @Override
    public KycExchangeResult doKycExchange(String relyingPartyId, String clientId, KycExchangeDto kycExchangeDto) throws KycExchangeException {
        try {
            // Call SingPass user info API
            SingPassUserInfo userInfo = singPassClient.getUserInfo(kycExchangeDto.getKycToken());

            // Encrypt and sign the user data
            String encryptedData = encryptData(userInfo);
            String signedData = signData(encryptedData);

            return new KycExchangeResult(encryptedData, signedData);
        } catch (Exception e) {
            throw new KycExchangeException("Failed to exchange KYC token with SingPass", e);
        }
    }

    @Override
    public SendOtpResult sendOtp(String relyingPartyId, String clientId, SendOtpDto sendOtpDto) throws SendOtpException {
        try {
            // Call SingPass OTP API
            SingPassOtpResponse otpResponse = singPassClient.sendOtp(sendOtpDto.getIndividualId(), sendOtpDto.getChannel());

            return new SendOtpResult(otpResponse.getStatus());
        } catch (Exception e) {
            throw new SendOtpException("Failed to send OTP via SingPass", e);
        }
    }

    @Override
    public boolean isSupportedOtpChannel(String channel) {
        return List.of("SMS", "EMAIL").contains(channel.toUpperCase());
    }

    @Override
    public List<KycSigningCertificateData> getAllKycSigningCertificates() throws KycSigningCertificateException {
        try {
            // Fetch certificates from SingPass or local keystore
            return singPassClient.getSigningCertificates();
        } catch (Exception e) {
            throw new KycSigningCertificateException("Failed to fetch KYC signing certificates", e);
        }
    }

    
}