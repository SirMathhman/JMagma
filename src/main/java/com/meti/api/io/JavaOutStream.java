package com.meti.api.io;

import java.io.IOException;
import java.io.OutputStream;

public class JavaOutStream implements OutStream {
	private final OutputStream outputStream;

	public JavaOutStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void write(int value) throws IOException {
		outputStream.write(value);
	}

	@Override
	public void flush() throws IOException {
		outputStream.flush();
	}

	@Override
	public void close() throws IOException {
		outputStream.close();
	}
}
