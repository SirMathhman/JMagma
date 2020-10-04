package com.meti.api.core;

import com.meti.api.string.String_;

public class Exception extends RuntimeException {
    public Exception(String message) {
        super(message);
    }

    public Exception(String_ message) {
        super(message.__());
    }
}
