package com.meti.compile;

import com.api.core.Option;
import com.api.io.Directory;
import com.api.io.File;
import com.api.io.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectorySource implements Source {
	private final Directory root;

	public DirectorySource(Directory root) {
		this.root = root;
	}

	@Override
	public Option<String> read(Script script) throws IOException {
		var path = root.asPath();
		var reduce = script.stream().reduce(path, Path::resolve, (path1, path2) -> path2);
		return reduce.existingAsFile().mapExceptionally(File::readString);
	}

	@Override
	public List<Script> list() throws IOException {
		var absolute = root.asPath().asAbsolute();
		var rootNames = absolute.names();
		var files = root.walk();
		var packages = new ArrayList<Script>();
		for (Path file : files) {
			if (file.hasExtensionOf("mg")) {
				var pathNames = file.asAbsolute().names();
				for (String rootName : rootNames) {
					pathNames.remove(rootName);
				}
				packages.add(new ListScript(pathNames));
			}
		}
		return packages;
	}
}
