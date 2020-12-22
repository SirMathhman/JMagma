package com.meti.compile;

import com.meti.api.io.NIOFileSystem;
import com.meti.api.io.Path;

import java.io.IOException;

public class DirectoryTarget {
	public DirectoryTarget() {
	}

	void write(Script script, String value) throws IOException {
		var root = NIOFileSystem.NIOFileSystem_.Root();
		var outputPath = script.stream().reduce(root, Path::resolve, (path, path2) -> path2);
		outputPath.ensureAsFile().writeString(value);
	}
}