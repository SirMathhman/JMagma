package com.meti;

import java.io.IOException;

public interface File {
	void deleteIfExists() throws IOException;

	String readAsString() throws IOException;

	void write(String sourceString) throws IOException;
}
