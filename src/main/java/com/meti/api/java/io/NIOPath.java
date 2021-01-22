package com.meti.api.java.io;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.java.core.JavaOption;
import com.meti.api.magma.collect.*;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class NIOPath implements Path {
	protected final java.nio.file.Path value;

	public NIOPath(java.nio.file.Path value) {
		this.value = value;
	}

	@Override
	public Path apply(String name) {
		return new NIOPath(value.resolve(name));
	}

	@Override
	public Directory ensureAsDirectory() throws IOException_ {
		try {
			if (!Files.exists(value)) Files.createDirectories(value);
			return new NIODirectory(value);
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

	@Override
	public File ensureAsFile() throws IOException_ {
		try {
			if (!Files.exists(value)) Files.createFile(value);
			return new NIOFile(value);
		} catch (IOException e) {
			throw new IOException_(e);
		}
	}

	@Override
	public Option<File> existingAsFile() {
		return new JavaOption<File>(existingAsFile1());
	}

	private Optional<File> existingAsFile1() {
		return exists() ? Optional.of(new NIOFile(value)) : Optional.empty();
	}

	@Override
	public boolean exists() {
		return Files.exists(value);
	}

	@Override
	public Sequence<String> listNames() throws CollectionException {
		return Streams.ofIntRange(0, value.getNameCount())
				.map(value::getName)
				.map(java.nio.file.Path::toString)
				.fold(ArrayLists.empty(), List::add);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (NIOPath) obj;
		return Objects.equals(this.value, that.value);
	}

	@Override
	public String toString() {
		try {
			return JavaLists.toJava(listNames())
					.stream()
					.map("\"%s\""::formatted)
					.collect(Collectors.joining(",", "[", "]"));
		} catch (CollectionException e) {
			return "";
		}
	}

	public java.nio.file.Path value() {
		return value;
	}

}
