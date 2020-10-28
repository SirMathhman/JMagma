package com.meti.api.log;

import com.meti.api.core.Exception;

public class SimpleLogger extends AbstractLogger {
    @Override
    protected String formatWithCause(Level level, String message, Exception exception) {
        String stackTrace = exception.renderStackTrace();
        return "%s: %s:\n%s".formatted(level.name(), message, stackTrace);
    }

    @Override
    protected String format(Level level, String message) {
        return "%s: %s".formatted(level.name(), message);
    }
}