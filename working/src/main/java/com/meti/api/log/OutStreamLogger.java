package com.meti.api.log;

import com.meti.api.io.OutStream;

import java.io.*;

public record OutStreamLogger(OutStream stream) implements Logger {
	@Override
	public void log(String name, String message, Exception e) {

	}

	@Override
	public void log(String name, String message) {

	}

	@Override
	public void log(Level level, String message, Exception e) {
		var writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		log(level, message + " - " + writer.toString());
	}

	@Override
	public void log(Level level, String message) {
		try {
			var formatted = "[" + level.name() + "]: " + message;
			var array = formatted.toCharArray();
			for (int i = 0; i < array.length; i++) {
				stream.write(array[i]);
			}
			stream.flush();
		} catch (IOException ignored) {
		}
	}
}
