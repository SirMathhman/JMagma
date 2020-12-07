package com.meti.api.io.file;

import com.meti.api.collect.Sequence;
import com.meti.api.core.Comparable;
import com.meti.api.core.Option;
import com.meti.api.core.Stringable;

import java.io.IOException;

public interface Path extends Comparable<Path>, Stringable {
	Sequence<String> computeNames();

	File ensuringAsFile() throws IOException;

	Option<File> existingAsFile();

	Option<Directory> existingAsDirectory();

	boolean isFile();

	boolean isDirectory();

	Option<String> computeExtension();
}
