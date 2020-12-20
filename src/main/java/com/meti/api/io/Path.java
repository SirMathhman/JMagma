package com.meti.api.io;

import com.meti.api.core.Option;

import java.io.IOException;
import java.util.List;

public interface Path {
	Path asAbsolute();

	Option<File> existing();

	File ensureAsFile() throws IOException;

	File createAsFile() throws IOException;

	boolean doesNotExist();

	boolean exists();

	File asFile();

	List<File> list();

	List<File> walk();

	Path resolveSibling(String name, String extension);

	boolean hasExtensionOf(String extension);

	String name();

	Option<Directory> existingAsDirectory();

	Directory ensuringAsDirectory() throws IOException;

	Path resolve(String name);
}
