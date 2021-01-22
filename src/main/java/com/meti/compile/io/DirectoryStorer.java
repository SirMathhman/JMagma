package com.meti.compile.io;

import com.meti.api.magma.collect.ArrayLists;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.collect.StreamException;
import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.Supplier;
import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.File;
import com.meti.api.magma.io.IOException_;
import com.meti.compile.CompileException;

public record DirectoryStorer(Directory root) implements Storer<List<File>> {
	@Override
	public List<File> write(Result result) throws IOException_, CompileException {
		try {
			return writeExceptionally(result);
		} catch (StreamException e) {
			throw new IOException_(e);
		}
	}

	private List<File> writeExceptionally(Result result) throws StreamException {
		var writeSource = (F1E1<Source, List<File>, CompileException>) source -> {
			var noMapping = (Supplier<CompileException>) () -> {
				var format = "No mapping was found for: %s";
				var message = format.formatted(source);
				return new CompileException(message);
			};

			var parent = resolveParent(source);
			var name = source.createName();
			var option = result.apply(source);
			var mapping = option.orElseThrow(noMapping);

			var writeFormat = (F1E1<Result.Format, File, Exception>) format -> {
				var formattedName = format.format(name);
				var content = mapping.apply(format);
				return parent.resolve(formattedName)
						.ensureAsFile()
						.writeAsString(content);
			};
			try {
				return mapping.streamFormats()
						.map(writeFormat)
						.fold(ArrayLists.empty(), List::add);
			} catch (StreamException e) {
				throw new CompileException(e);
			}
		};
		return result.streamSources()
				.map(writeSource)
				.fold(ArrayLists.empty(), Lists::addAll);
	}

	private Directory resolveParent(Source source) throws CompileException {
		try {
			return source.streamParent().fold(root,
					(current, directory) ->
							current.resolve(directory).ensureAsDirectory());
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}
}
