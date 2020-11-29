package com.meti.api.extern;

import com.meti.api.io.InStream;

import java.io.IOException;
import java.io.InputStream;

public class JavaInStream implements InStream {
	private final InputStream inputStream;

	public JavaInStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

	@Override
	public void close() throws IOException {
		inputStream.close();
	}
}
