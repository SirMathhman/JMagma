package com.meti.api.log;

import com.meti.api.core.Exception;

public interface Logger {
    void logWithException(Level level, String message, Exception exception);

    void log(Level level, String message);

    enum Level {
        Info,
        Warning,
        Severe
    }
}
