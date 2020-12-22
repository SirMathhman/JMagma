package com.api.io;

import java.io.IOException;

public interface File {
	String readString() throws IOException;

	void delete() throws IOException;

	Path asPath();

	Path writeString(String output) throws IOException;
}
