package com.meti.api.io;

import java.io.IOException;
import java.io.OutputStream;

public record JavaOutStream(OutputStream stream) implements OutStream {
	@Override
	public void write(char b) throws IOException {
		stream.write(b);
	}

	@Override
	public void flush() throws IOException {
		stream.flush();
	}
}
