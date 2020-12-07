package com.meti.exec.compile;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.collect.string.Strings;
import com.meti.api.extern.ExceptionFunction0;
import com.meti.api.extern.Function0;
import com.meti.api.io.file.Directory;
import com.meti.api.io.file.File;
import com.meti.api.io.file.Path;

public class PathLoader {
	private final List<File> paths;

	public PathLoader() {
		this(ArrayList.empty(File::compareTo));
	}

	public PathLoader(List<File> paths) {
		this.paths = paths;
	}

	public PathLoader add(Path path) throws LoadException {
		Function0<LoadException> loadExceptionFunction0 = () -> {
			var asString = path.asString();
			var concat = Strings.concat(asString, " doesn't seem to be either a file or directory.");
			return new LoadException(concat);
		};
		ExceptionFunction0<PathLoader, LoadException> addAsDirectory = () -> path.existingAsDirectory()
				.map(PathLoader.this::addAll)
				.orElseThrow(loadExceptionFunction0);
		return path.existingAsFile()
				.filter(this::isMagmaFile)
				.map(paths::add)
				.map(PathLoader::new)
				.orElseGet(addAsDirectory);
	}

	private boolean isMagmaFile(File file) {
		return file.asPath()
				.computeExtension()
				.filter(s -> Strings.equals(s, "mgs"))
				.isPresent();
	}

	private PathLoader addAll(Directory directory) {
		try {
			var newList = directory.listFiles().foldLeft(paths, List::add);
			var loader = new PathLoader(newList);
			return directory.listDirectories().foldLeft(loader, PathLoader::addAll);
		} catch (StreamException e) {
			return this;
		}
	}
}
