package io.mosip.esignet.core.exception;

public class EsignetException extends Exception {
    public EsignetException(String message) {
        super(message);
    }

    public EsignetException(String message, Throwable cause) {
        super(message, cause);
    }
}