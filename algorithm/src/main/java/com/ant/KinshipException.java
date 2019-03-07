package com.ant;

public class KinshipException extends RuntimeException {
    public KinshipException() {
    }

    public KinshipException(String message) {
        super(message);
    }

    public KinshipException(String message, Throwable cause) {
        super(message, cause);
    }
}
