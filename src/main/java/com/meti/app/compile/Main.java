package com.meti.app.compile;

import com.meti.api.io.JavaPath;
import com.meti.api.io.Path;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

import static com.meti.api.io.JavaPath.*;

public class Main {
    public static void main(String[] args) {
        Path path = Root().resolveName(".build");
        if (!path.exists()) {
            try {
                String format = "Build did not exist at '%s' and will be created.";
                log(Level.Warning, format.formatted(path.toString()));
                path.createAsFile();
            } catch (IOException e) {
                logWithException(Level.Severe, "Failed to create build file", e);
            }
        }
    }

    private static void logWithException(Level level, final String message, IOException exception) {
        StringWriter writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(writer));
        System.err.printf("%s -- %s: %s:\n%s%n", Instant.now(), level.name(), message, writer.toString());
    }

    private static void log(Level level, final String message) {
        System.err.printf("%s -- %s: %s", Instant.now(), level.name(), message);
    }

    enum Level {
        Info, Warning, Severe
    }
}
