package com.meti.api.io;

import java.io.IOException;

public interface OutStream extends Closeable {
	void write(int value) throws IOException;

	void flush() throws IOException;
}
