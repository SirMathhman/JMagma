package com.meti.api.io.file.nio;

import com.meti.api.collect.Sequence;
import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.collect.string.Strings;
import com.meti.api.core.Option;
import com.meti.api.core.Primitives;
import com.meti.api.io.file.Directory;
import com.meti.api.io.file.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static com.meti.api.core.Some.Some;
import static com.meti.api.io.file.nio.NIODirectory.NIODirectory;

public class NIOPath implements com.meti.api.io.file.Path {
	private final Path path;

	private NIOPath(Path path) {
		this.path = path;
	}

	public static com.meti.api.io.file.Path NIOPath(Path path) {
		return new NIOPath(path);
	}

	@Override
	public Sequence<String> computeNames() {
		try {
			return SequenceStream(ArrayList.range(0, path.getNameCount(), Primitives::comparingInts, value -> value + 1))
					.map(path::getName)
					.map(Path::toString)
					.foldLeft(ArrayList.empty(Strings::compareTo), List::add);
		} catch (StreamException e) {
			return ArrayList.empty(String::compareTo);
		}
	}

	@Override
	public File ensuringAsFile() throws IOException {
		if (!Files.exists(path)) Files.createFile(path);
		return NIOFile.NIOFile(path);
	}

	@Override
	public Option<File> existingAsFile() {
		return Some(path)
				.filter(Files::exists)
				.filter(Files::isRegularFile)
				.map(NIOFile::NIOFile);
	}

	@Override
	public Option<Directory> existingAsDirectory() {
		return Some(path)
				.filter(Files::exists)
				.filter(Files::isDirectory)
				.map(NIODirectory::NIODirectory);
	}

	@Override
	public boolean isFile() {
		return Files.isRegularFile(path);
	}

	@Override
	public Option<String> computeExtension() {
		var fileName = computeFileName();
		var asString = fileName.toString();
		return Some(asString)
				.map(string -> string.indexOf('.'))
				.filter(index -> index != -1)
				.map(index -> asString.substring(index + 1))
				.map(Strings::trim);
	}

	@Override
	public Path computeFileName() {
		return path.getName(path.getNameCount() - 1);
	}

	@Override
	public Directory ensuringAsDirectory() throws IOException {
		if (!Files.exists(path)) Files.createDirectory(path);
		if (!Files.isDirectory(path)) throw new IOException(path + " isn't a directory.");
		return NIODirectory(path);
	}

	@Override
	public int compareTo(com.meti.api.io.file.Path o) {
		return computeNames().compareTo(o.computeNames());
	}

	@Override
	public String asString() {
		return path.toString();
	}
}
