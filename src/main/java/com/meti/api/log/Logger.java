package com.meti.api.log;

import java.io.IOException;

public interface Logger {
    default Logger logIgnored(Level level, String message) {
        try {
            return log(level, message);
        } catch (IOException ignored) {
            /*
            Yes, I know that it's not good practice to ignore exceptions. However, there exists moments
            where this behaviour might pose great inconvenient towards the user, e.g. having to catch exceptions when
            logging exceptions. One doesn't need to be a genuis to figure out the kinds of problems that will cause.

            Also, printing to something like System.out probably won't throw an exception (I hope it doesn't).
             */
            return this;
        }
    }

    default Logger logExceptionallyIgnored(Level level, String message, Exception exception) {
        try {
            return logExceptionally(level, message, exception);
        } catch (IOException ignored) {
            return this;
        }
    }

    Logger log(Level level, String message) throws IOException;

    Logger logExceptionally(Level level, String message, Exception exception) throws IOException;

    enum Level {
        Info,
        Warning,
        Severe
    }
}
