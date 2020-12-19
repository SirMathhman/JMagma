package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;

public interface Path {
	Path asAbsolute();

	Option<File> existing();

	File ensureAsFile() throws IOException;

	File createAsFile() throws IOException;

	boolean doesNotExist();

	Path resolve(String name);

	boolean exists();

	File asFile();
}
