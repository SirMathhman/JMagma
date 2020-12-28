package com.meti.api.log;

import java.io.IOException;

public interface Logger {
	@Deprecated
	void log(String name, String message, Exception e);

	@Deprecated
	void log(String name, String message);

	void log(Level level, String message, Exception e);

	void log(Level level, String message) throws IOException;

	enum Level {
		Info,
		Warning,
		Severe
	}
}
