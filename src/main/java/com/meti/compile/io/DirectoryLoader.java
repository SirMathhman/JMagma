package com.meti.compile.io;

import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.IOException_;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record DirectoryLoader(Directory root) implements Loader {
	private static final String AsFile = "%s.mg";
	private static final String AsScript = "%s.mgs";

	@Override
	public List<Source> listScripts() throws IOException_ {
		return streamScripts().collect(Collectors.toList());
	}

	@Override
	public String load(Source source) throws IOException_ {
		var size = source.size();
		var current = root;
		for (int i = 0; i < size - 1; i++) {
			var name = source.apply(i);
			var path = current.resolve(name);
			current = path.ensureAsDirectory();
		}
		var last = source.apply(size - 1);
		var asFile = current.resolve(AsFile.formatted(last));
		var asScript = current.resolve(AsScript.formatted(last));
		var isFile = asFile.exists();
		var isScript = asScript.exists();
		if (isFile || isScript) {
			if (isFile && isScript) {
				throw new IOException_("Both a file and a script is present.");
			} else if (isFile) {
				return asFile.ensureAsFile().readAsString();
			} else {
				return asScript.ensureAsFile().readAsString();
			}
		}
		throw new IOException_("Source '%s' doesn't exist in directory '%s'".formatted(source, root));
	}

	private Stream<Source> streamScripts() throws IOException_ {
		var paths = root.streamTree().collect(Collectors.toList());
		var sources = new ArrayList<Source>();
		for (int i = 0; i < paths.size(); i++) {
			var path = paths.get(i);
			var names = path.listNames();
			var lastName = names.get(names.size() - 1);
			if (lastName.endsWith(".mg") || lastName.endsWith(".mgs")) {
				var relativized = root.relativize(path);
				var relativizedNames = relativized.listNames();
				sources.add(new ListSource(relativizedNames));
			}
		}
		return sources.stream();
	}
}
