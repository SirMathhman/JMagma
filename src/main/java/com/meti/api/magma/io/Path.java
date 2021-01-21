package com.meti.api.magma.io;

import java.util.List;
import java.util.Optional;

public interface Path {
	Path apply(String name);

	Directory ensureAsDirectory() throws IOException_;

	File ensureAsFile() throws IOException_;

	Optional<File> existing();

	boolean exists();

	List<String> listNames();

	@Deprecated
	String readString() throws IOException_;

	@Deprecated
	void writeString(String content) throws IOException_;
}
