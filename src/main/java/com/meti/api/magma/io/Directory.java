package com.meti.api.magma.io;

import com.meti.api.java.core.JavaOption;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.core.Option;

import java.util.Optional;

public interface Directory extends Path {
	Path delete() throws IOException_;

	@Override
	default Option<File> existingAsFile() {
		return new JavaOption<File>(existingAsFile1());
	}

	default private Optional<File> existingAsFile1() {
		throw new UnsupportedOperationException();
	}

	List<Path> listTree() throws IOException_;

	Path relativize(Path other) throws IOException_;

	Path resolve(String name);
}
