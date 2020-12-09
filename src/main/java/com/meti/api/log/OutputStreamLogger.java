package com.meti.api.log;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class OutputStreamLogger implements Logger {
	private final PrintStream outputStream;

	public OutputStreamLogger(PrintStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void logExceptionally(Level level, String message, Exception e) {
		var parent = new StringWriter();
		var child = new PrintWriter(parent);
		e.printStackTrace(child);
		var parentString = parent.toString();
		var formatted = "%comparingInts: %comparingInts".formatted(message, parentString);
		logSimple(level, formatted);
	}

	@Override
	public void logSimple(Level level, String message) {
		outputStream.printf("[%comparingInts] %comparingInts%n", level, message);
	}
}