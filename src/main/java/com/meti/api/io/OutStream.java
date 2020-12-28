package com.meti.api.io;

import java.io.IOException;

public interface OutStream {
	void write(char b) throws IOException;

	void flush() throws IOException;
}
