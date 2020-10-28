package com.meti.api.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SimpleLogger extends AbstractLogger {
    @Override
    protected String formatWithCause(Level level, String message, IOException exception) {
        StringWriter writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(writer));
        return "%s: %s:\n%s".formatted(level.name(), message, writer.toString());
    }

    @Override
    protected String format(Level level, String message) {
        return "%s: %s".formatted(level.name(), message);
    }
}