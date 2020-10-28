package com.meti.api.core;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Exception extends java.lang.Exception {
    public Exception(Throwable cause) {
        super(cause);
    }

    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public String renderStackTrace() {
        StringWriter writer = new StringWriter();
        printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}
