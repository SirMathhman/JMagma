package com.meti;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {
	void logExceptionally(Level level, String message, Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		logFormatted(level, "%s\n%s", message, writer);
	}

	void log(Level level, String message) {
		System.out.printf("%s: %s%n", level.name(), message);
	}

	void logFormatted(Level level, String format, Object... args) {
		log(level, format.formatted(args));
	}

	enum Level {
		Info,
		Warning,
		Error
	}
}