package com.meti.api.io;

import java.io.IOException;

public interface File {
	String readString() throws IOException;

	Path asPath();

	NIOFile writeString(String output) throws IOException;
}
