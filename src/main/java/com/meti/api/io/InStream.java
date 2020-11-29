package com.meti.api.io;

import java.io.IOException;

public interface InStream extends Closeable {
	int EndOfFile = -1;

	int read() throws IOException;
}
