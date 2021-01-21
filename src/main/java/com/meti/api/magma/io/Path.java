package com.meti.api.magma.io;

import com.meti.api.magma.collect.List;
import com.meti.api.magma.core.Option;

public interface Path {
	Path apply(String name);

	Directory ensureAsDirectory() throws IOException_;

	File ensureAsFile() throws IOException_;

	Option<File> existingAsFile();

	boolean exists();

	List<String> listNames();
}
