package com.meti.api.collect;

import com.meti.api.core.Exception;
import com.meti.api.string.String_;

public class IndexException extends Exception {
    public IndexException(String message) {
        super(message);
    }

    public IndexException(String_ message) {
        super(message);
    }
}
