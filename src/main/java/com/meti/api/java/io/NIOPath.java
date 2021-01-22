package com.meti.api.java.io;

import com.meti.api.java.collect.JavaList;
import com.meti.api.java.core.JavaOption;
import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
	public com.meti.api.magma.collect.List<String> listNames() {
		return new JavaList<>(listNames1());
	}

	private List<String> listNames1() {
		var names = new ArrayList<String>();
		var count = value.getNameCount();
		for (int i = 0; i < count; i++) {
			names.add(value.getName(i).toString());
		}
		return names;
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
		return JavaLists.toJava(listNames())
				.stream()
				.map("\"%s\""::formatted)
				.collect(Collectors.joining(",", "[", "]"));
	}

	public java.nio.file.Path value() {
		return value;
	}

}
