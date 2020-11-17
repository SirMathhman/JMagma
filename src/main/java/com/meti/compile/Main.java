package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.api.io.Directory;
import com.meti.api.io.Extant;
import com.meti.api.io.Path;
import com.meti.api.log.Logger;
import com.meti.api.stream.StreamException;
import com.meti.compile.path.NIOScriptPath;
import com.meti.compile.path.ScriptPath;

import java.io.IOException;
import java.util.function.Function;

import static com.meti.api.collect.ArrayList.ArrayList;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.io.JavaOutStream.OutStream;
import static com.meti.api.io.NIOFileSystem.FileSystem_;
import static com.meti.api.log.Logger.Level.*;
import static com.meti.api.log.OutStreamLogger.Logger;
import static com.meti.api.stream.ListStream.ListStream;
import static com.meti.compile.MagmaCompiler.MagmaCompiler;

public class Main {
	private static final Logger LOGGER = Logger(OutStream(System.out));
	private static final String SourceFormat = "%s.mg";

	private static Option<Directory> Root() {
		try {
			return Some(FileSystem_.Root());
		} catch (IOException e) {
			LOGGER.logExceptionallyIgnored(Severe, "Failed to find root.", e);
			return None();
		}
	}

	public static void main(String[] args) {
		Root().map(Main::execute).orElse(-1);
	}

	private static int execute(Directory root) {
		Path sourceDirectory = root.resolve("source");
		Path build = root.resolve(".build");
		Path target = root.resolve("target.c");
		int i = 0;
		boolean finished = false;
		try {
			Directory directory = sourceDirectory.ensuringAsDirectory();
			try {
				String content = build.ensuringAsFile().readAsString();
				if (!content.isBlank()) {
					String trimmed = content.trim();
					int separator = trimmed.lastIndexOf('.');
					String packageSlice = trimmed.substring(0, separator);
					String packageTrim = packageSlice.trim();

					String nameSlice = trimmed.substring(separator + 1);
					String name = nameSlice.trim();

					String[] packageArray = packageTrim.split("\\.");
					String formattedName = SourceFormat.formatted(name);

					Path result;
					try {
						result = ListStream(ArrayList(packageArray))
								.foldLeft(directory, Directory::resolveDirectory)
								.resolve(formattedName);
					} catch (StreamException e2) {
						Path resolve = directory.resolve(formattedName);
						String format2 = "Failed to stream package, and '%s' will be used as entry.";
						String message2 = format2.formatted(resolve);
						LOGGER.logExceptionallyIgnored(Severe, message2, e2);
						result = resolve;
					}

					Path mainFile;
					if (trimmed.contains(".")) {
						mainFile = result;
					} else {
						String format2 = "%s.mg";
						String formatted = format2.formatted(trimmed);
						mainFile = directory.resolve(formatted);
					}
					Function<Extant, String> compile = file -> {
						String value;
						try {
							value = file.readAsString();
						} catch (IOException e1) {
							String format1 = "Failed to read main file at '%s'.";
							String message1 = format1.formatted(file);
							LOGGER.logExceptionallyIgnored(Severe, message1, e1);
							value = "";
						}
						ScriptPath scriptPath = new NIOScriptPath(directory);
						Compiler compiler = MagmaCompiler(scriptPath);
						return compiler.compile(value);
					};
					var output = mainFile
							.mapExistenceAsFile(compile)
							.orElseSupply(() -> {
								String format1 = "Entry point at '%s' did not exist.";
								String message1 = format1.formatted(mainFile);
								LOGGER.logIgnored(Severe, message1);
								return "";
							});
					try {
						target.ensuringAsFile().write(output);
						i = ((CharSequence) output).length();
						finished = true;
					} catch (IOException e) {
						String format1 = "Failed to write output to '%s' of size %d.";
						String message1 = format1.formatted(target, ((CharSequence) output).length());
						LOGGER.logIgnored(Severe, message1);
						finished = true;
					}
				}
				if (!finished) {
					LOGGER.logIgnored(Severe, "No entry point was found.");
				}
			} catch (IOException e) {
				LOGGER.logExceptionallyIgnored(Warning, "Failed to read build file.", e);
			}
		} catch (IOException e) {
			String format0 = "Failed to create source directory at '%s'.";
			String message0 = format0.formatted(sourceDirectory);
			LOGGER.logExceptionallyIgnored(Severe, message0, e);
		}
		String format = "Wrote %d characters to target at '%s'.";
		String message = format.formatted(i, target);
		LOGGER.logIgnored(Info, message);
		return i;
	}
}
