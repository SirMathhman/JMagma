package com.meti.api.io;

import com.meti.api.extern.ExceptionFunction1;

import java.io.IOException;
import java.io.InputStream;

public class JavaInStream extends AbstractInStream {
	private final InputStream inputStream;

	public JavaInStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void close() throws IOException {
		inputStream.close();
	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}
}
