package com.meti;

import com.meti.api.extern.Function1;

import java.io.IOException;
import java.io.InputStream;

public class Executor {
	void execute(InputStream stream, Function1<String, Boolean> filter) throws IOException {
		StringBuilder buffer = new StringBuilder();
		boolean exiting;
		int c;
		do {
			c = stream.read();
			if (c == '\n') {
				String line = buffer.toString();
				buffer = new StringBuilder();
				exiting = filter.apply(line);
			} else {
				buffer.append((char) c);
				exiting = false;
			}
		} while (c != -1 && !exiting);
	}
}
