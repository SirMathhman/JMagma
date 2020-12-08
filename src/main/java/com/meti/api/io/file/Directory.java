package com.meti.api.io.file;

import com.meti.api.collect.stream.Stream;

import java.io.IOException;

public interface Directory {
	Path resolve(String child);

	Stream<File> listFiles() throws IOException;

	Stream<Directory> listDirectories() throws IOException;
}
