package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;

public interface PrimitiveInStream extends InStream {
	Option<String> readLine() throws IOException;
}
