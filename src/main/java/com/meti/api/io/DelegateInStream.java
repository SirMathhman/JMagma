package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class DelegateInStream implements PrimitiveInStream {
	private final InStream parent;

	private DelegateInStream(InStream parent) {
		this.parent = parent;
	}

	public static DelegateInStream DelegateInStream(InStream parent) {
		return new DelegateInStream(parent);
	}

	@Override
	public Option<String> readLine() throws IOException {
		StringBuilder builder = new StringBuilder();
		int c = parent.read();
		if (c == EndOfFile) return None();
		while (c != EndOfFile && c != '\n') {
			builder.append((char) c);
			c = parent.read();
		}
		return Some(builder.toString());
	}

	@Override
	public int read() throws IOException {
		return parent.read();
	}

	@Override
	public void close() throws IOException {
		parent.close();
	}
}
