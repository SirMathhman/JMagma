package com.meti.api.log;

public interface Logger {
	void logExceptionally(Level level, String message, Exception e);

	void logSimple(Level level, String message);

	public enum Level {
		Warning,
		Error
	}
}
