package com.educerts.esignet.singpass;

public class SingPassAuthResponse {
    private String accessToken; // Access token for authenticated sessions
    private String userId;      // Unique identifier for the user
    private String expiresIn;   // Token expiration time

    // Getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}

public class SingPassUserInfo {
    private String name;
    private String email;
    private String phoneNumber;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

public class SingPassOtpResponse {
    private String status; // Status of the OTP request (e.g., "SUCCESS", "FAILED")
    private String otpReferenceId; // Reference ID for the OTP

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtpReferenceId() {
        return otpReferenceId;
    }

    public void setOtpReferenceId(String otpReferenceId) {
        this.otpReferenceId = otpReferenceId;
    }
}