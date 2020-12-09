package com.meti.exec.compile.source;

import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.collect.string.StringBuffer;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.io.InStream;
import com.meti.api.io.file.Directory;
import com.meti.api.io.file.File;
import com.meti.exec.compile.Package;

import java.io.IOException;

import static com.meti.api.collect.string.SimpleStringBuffer.StringBuffer;
import static com.meti.exec.compile.source.LoadException.LoadException;

public class DirectorySource implements Source {
	private final Directory directory;

	public DirectorySource(Directory directory) {
		this.directory = directory;
	}

	@Override
	public String load(Package p) throws LoadException {
		ExceptionFunction2<Directory, String, Directory, LoadException> resolveDirectory = (directory, name) -> {
			try {
				return directory.resolve(name).ensuringAsDirectory();
			} catch (IOException e) {
				throw LoadException(e);
			}
		};
		ExceptionFunction1<Stream<String>, Directory, LoadException> foldDirectory = stream -> {
			try {
				return stream.foldLeft(directory, resolveDirectory);
			} catch (StreamException e) {
				throw LoadException(e);
			}
		};
		ExceptionFunction2<Directory, String, File, LoadException> resolveFile = (directory, s) -> {
			try {
				return directory.resolve(s).ensuringAsFile();
			} catch (IOException e) {
				throw LoadException(e);
			}
		};
		ExceptionFunction1<InStream, String, StreamException> mapper = inStream -> inStream.stream()
				.map(integer -> (char) (int) integer)
				.foldLeft(StringBuffer(), StringBuffer::append)
				.asString();
		try {
			return p.apply(foldDirectory, resolveFile).read().enclosing(mapper);
		} catch (StreamException | IOException e) {
			throw LoadException(e);
		}
	}
}
