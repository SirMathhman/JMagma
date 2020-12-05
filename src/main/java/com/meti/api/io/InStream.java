package com.meti.api.io;

import com.meti.api.collect.stream.Stream;

import java.io.IOException;

public interface InStream extends Closeable<InStream> {
	int EndOfFile = -1;

	int read() throws IOException;

	Stream<Integer> stream();
}
