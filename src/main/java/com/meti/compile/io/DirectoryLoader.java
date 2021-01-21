package com.meti.compile.io;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.IOException_;
import com.meti.api.magma.io.Path;

import java.util.ArrayList;
import java.util.List;

public record DirectoryLoader(Directory root) implements Loader {
	private static final String FileExtension = ".mg";
	private static final String ScriptExtension = ".mgs";
	private static final String AsFile = "%s.mg";
	private static final String AsScript = "%s.mgs";

	@Override
	public List<Source> listSources() throws IOException_ {
		var tree = root.listTree();
		var sources = new ArrayList<Source>();
		for (int i = 0; i < tree.size(); i++) {
			Path result;
			try {
				result = tree.apply(i);
			} catch (IndexException e) {
				throw new UnsupportedOperationException(e);
			}
			var path = result;
			var names = Lists.fromJava(path.listNames());
			var lastName = names.get(names.size() - 1);
			var isFile = lastName.endsWith(FileExtension);
			var isScript = lastName.endsWith(ScriptExtension);
			if (isFile || isScript) {
				var relativized = root.relativize(path);
				var relativizedNames = Lists.fromJava(relativized.listNames());
				var parent = relativizedNames.subList(0, relativizedNames.size() - 1);
				var name = relativizedNames.get(relativizedNames.size() - 1);
				var extension = isFile ? FileExtension : ScriptExtension;
				var newName = name.substring(0, name.length() - extension.length());
				var newList = new ArrayList<>(parent);
				newList.add(newName);
				sources.add(new ListSource(newList));
			}
		}
		return sources;
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
		var format = "Even though the source '%s' is listed, neither '%s' or '%s' exist.";
		var message = format.formatted(source, asFile, asScript);
		throw new IOException_(message);
	}
}
