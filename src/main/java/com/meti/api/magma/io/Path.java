package com.meti.api.magma.io;

import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.core.Option;

public interface Path {
	Path apply(String name);

	Directory ensureAsDirectory() throws IOException_;

	File ensureAsFile() throws IOException_;

	Option<File> existingAsFile();

	boolean exists();

	Sequence<String> listNames();
}
