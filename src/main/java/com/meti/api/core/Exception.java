package com.meti.api.core;

public class Exception extends java.lang.Exception {
    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
