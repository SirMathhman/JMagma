package com.meti.api.io;

import java.io.IOException;

public interface InStream extends Closeable {
	int read() throws IOException;
}
