package com.meti.api.io.file;

import com.meti.api.collect.stream.Stream;

public interface Directory {
	Path resolve(String child);

	Stream<File> listFiles();

	Stream<Directory> listDirectories();
}
