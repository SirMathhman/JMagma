package com.meti.api.io;

import com.meti.api.core.Exception;

public class IOException extends Exception {
    public IOException(String message) {
        super(message);
    }

    public IOException(Throwable cause) {
        super(cause);
    }

    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
}
