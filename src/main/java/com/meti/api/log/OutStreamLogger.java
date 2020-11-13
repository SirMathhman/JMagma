package com.meti.api.log;

import com.meti.api.io.OutStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class OutStreamLogger implements Logger {
    private final OutStream outStream;

    private OutStreamLogger(OutStream outStream) {
        this.outStream = outStream;
    }

    public static OutStreamLogger Logger(OutStream outStream) {
        return new OutStreamLogger(outStream);
    }

    @Override
    public Logger log(Level level, String message) throws IOException {
        String s = "[%s]: %s".formatted(level, message);
        for (char c : s.toCharArray()) {
            outStream.write(c);
        }
        outStream.write('\n').flush();
        return this;
    }

    @Override
    public Logger logExceptionally(Level level, String message, Exception exception) throws IOException {
        StringWriter writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(writer));
        String s = "[%s]: %s\n%s".formatted(level, message, writer);
        for (char c : s.toCharArray()) {
            outStream.write(c);
        }
        outStream.write('\n').flush();
        return this;
    }
}
