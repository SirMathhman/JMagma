package com.meti.api.log;

import java.io.IOException;

public abstract class AbstractLogger implements Logger {
    @Override
    public void logWithException(Level level, final String message, IOException exception) {
        String formatted = formatWithCause(level, message, exception);
        System.err.println(formatted);
    }

    protected abstract String formatWithCause(Level level, String message, IOException exception);

    @Override
    public void log(Level level, final String message) {
        System.err.println(format(level, message));
    }

    protected abstract String format(Level level, String message);
}
