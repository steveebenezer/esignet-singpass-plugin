package com.educerts.esignet.singpass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SingPassAuthenticatorTest {

    private SingPassClient mockSingPassClient;
    private SingPassAuthenticator authenticator;

    @BeforeEach
    public void setUp() {
        // Mock the SingPassClient
        mockSingPassClient = mock(SingPassClient.class);
        authenticator = new SingPassAuthenticator(mockSingPassClient);
    }

    @Test
    public void testDoKycAuth_Success() throws Exception {
        // Mock SingPass authentication response
        SingPassAuthResponse authResponse = new SingPassAuthResponse();
        authResponse.setAccessToken("dummy-access-token");
        authResponse.setUserId("user-123");

        when(mockSingPassClient.authenticate("username", "password")).thenReturn(authResponse);

        // Call the method under test
        KycAuthDto kycAuthDto = new KycAuthDto("username", "password");
        KycAuthResult result = authenticator.doKycAuth("relyingPartyId", "clientId", kycAuthDto);

        // Verify the result
        assertNotNull(result.getKycToken());
        assertNotNull(result.getPsut());
    }

    @Test
    public void testDoKycAuth_Failure() throws Exception {
        // Mock SingPass authentication failure
        when(mockSingPassClient.authenticate("username", "password"))
            .thenThrow(new IOException("Authentication failed"));

        // Call the method under test
        KycAuthDto kycAuthDto = new KycAuthDto("username", "password");
        assertThrows(KycAuthException.class, () -> {
            authenticator.doKycAuth("relyingPartyId", "clientId", kycAuthDto);
        });
    }

    @Test
    public void testSendOtp_Success() throws Exception {
        // Mock SingPass OTP response
        SingPassOtpResponse otpResponse = new SingPassOtpResponse();
        otpResponse.setStatus("SUCCESS");
        otpResponse.setOtpReferenceId("otp-ref-123");

        when(mockSingPassClient.sendOtp("user-123", "SMS")).thenReturn(otpResponse);

        // Call the method under test
        SendOtpDto sendOtpDto = new SendOtpDto("user-123", "SMS");
        SendOtpResult result = authenticator.sendOtp("relyingPartyId", "clientId", sendOtpDto);

        // Verify the result
        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    public void testIsSupportedOtpChannel() {
        assertTrue(authenticator.isSupportedOtpChannel("SMS"));
        assertTrue(authenticator.isSupportedOtpChannel("EMAIL"));
        assertFalse(authenticator.isSupportedOtpChannel("WHATSAPP"));
    }
}