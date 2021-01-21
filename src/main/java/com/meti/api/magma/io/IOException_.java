package com.meti.api.magma.io;

import com.meti.api.magma.except.Exception;

public class IOException_ extends Exception {
	public IOException_() {
	}

	public IOException_(String message) {
		super(message);
	}

	public IOException_(String message, Throwable cause) {
		super(message, cause);
	}

	public IOException_(Throwable cause) {
		super(cause);
	}

	public IOException_(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
