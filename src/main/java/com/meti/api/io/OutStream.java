package com.meti.api.io;

import java.io.IOException;

public interface OutStream extends Closeable<OutStream> {
	OutStream write(int value) throws IOException;

	OutStream flush() throws IOException;
}
