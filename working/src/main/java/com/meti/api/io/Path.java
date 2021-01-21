package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.util.List;

public interface Path {
	Path asAbsolute();

	File ensureAsFile() throws IOException;

	File createAsFile() throws IOException;

	boolean doesNotExist();

	boolean exists();

	boolean hasExtensionOf(String extension);

	String name();

	Option<Directory> existingAsDirectory();

	Directory ensureDirectory() throws IOException;

	Path resolve(String name);

	Option<File> existingAsFile();

	List<String> names();

	Directory ensureDirectories() throws IOException;
}
