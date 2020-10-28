package com.meti.api.log;

import java.io.IOException;

public interface Logger {
    void logWithException(Level level, String message, IOException exception);

    void log(Level level, String message);

    public enum Level {
        Info,
        Warning,
        Severe
    }
}
