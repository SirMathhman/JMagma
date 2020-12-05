package com.meti.api.io;

import java.io.IOException;

public interface InStream {
	void close() throws IOException;

	int read() throws IOException;
}
