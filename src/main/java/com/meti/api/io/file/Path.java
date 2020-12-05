package com.meti.api.io.file;

import com.meti.api.core.Option;

import java.io.IOException;

public interface Path {
	Extant ensuringAsFile() throws IOException;

	Option<Extant> existingAsFile();

	boolean isExtant();

	Option<Directory> existingAsDirectory();
}
