package com.meti.compile;

import com.api.core.Option;
import com.api.io.File;
import com.api.io.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.api.core.None.None;
import static com.api.core.Some.Some;
import static com.api.io.NIOFileSystem.NIOFileSystem_;

public class Main {
	private static final MagmaCompiler Compiler = MagmaCompiler.MagmaCompiler_;
	private static final Logger logger = Logger.getAnonymousLogger();
	private static final Path Source = NIOFileSystem_.Root().resolve("source");

	public static void main(String[] args) {
		var children = readSourceDirectory();
		if (children.isEmpty()) {
			logger.log(Level.WARNING, "No source scripts were found in: " + Source.asAbsolute().toString());
		} else {
			var intermediates = new ArrayList<Path>();
			for (Path child : children) {
				var name = child.name();
				if (child.hasExtensionOf("mg")) {
					try {
						compileSource(intermediates, child, name);
					} catch (IOException e) {
						logger.log(Level.WARNING, "Failed to compile path: " + child, e);
					}
				}
			}
			if (intermediates.isEmpty()) {
				logger.log(Level.WARNING, "No intermediate files were found.");
			} else {
				compileIntermediates(intermediates);
				for (Path intermediate : intermediates) {
					deleteIntermediate(intermediate);
				}
			}
		}
	}

	private static void compileSource(ArrayList<Path> intermediates, Path child, String name) throws IOException {
		var file = child.ensureAsFile();
		var input = readContent(file);
		var output = compile(input);
		var intermediate = child.resolveSibling(name, "c");
		ensureIntermediatePath(intermediate)
				.map(file1 -> writeIntermediate(file1, output))
				.ifPresent(intermediates::add);
	}

	private static List<Path> readSourceDirectory() {
		try {
			return Source.ensuringAsDirectory().walk();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to read source directory.", e);
			return Collections.emptyList();
		}
	}

	private static Path writeIntermediate(File file, String output) {
		try {
			return file.writeString(output);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to write to intermediate file " + file + " with data size of " + output.length(), e);
			return file.asPath();
		}
	}

	private static void deleteIntermediate(Path resolve) {
		try {
			resolve.existing().ifPresentExceptionally(File::delete);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to delete intermediate file: " + resolve, e);
		}
	}

	private static void compileIntermediates(List<Path> intermediates) {
		try {
			compileIntermediatesExceptionally(intermediates);
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Failed to compile intermediate files.", e);
		}
	}

	private static void compileIntermediatesExceptionally(List<Path> intermediates) throws IOException, InterruptedException {
		var command = buildCommand(intermediates);
		var builder = new ProcessBuilder(command);
		var start = builder.start();
		start.getInputStream().transferTo(System.out);
		start.getErrorStream().transferTo(System.err);
		start.waitFor();
	}

	private static ArrayList<String> buildCommand(List<Path> intermediates) {
		var command = new ArrayList<>(List.of("gcc", "-o", "Main"));
		command.addAll(intermediates.stream()
				.map(Path::asAbsolute)
				.map(Path::toString)
				.collect(Collectors.toList()));
		return command;
	}

	private static Option<File> ensureIntermediatePath(Path intermediate) {
		try {
			return Some(intermediate.ensureAsFile());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to ensure intermediate path: " + intermediate, e);
			return None();
		}
	}

	private static String compile(String input) {
		if (input.isBlank()) {
			return "";
		} else {
			try {
				return Compiler.compile(input);
			} catch (CompileException e) {
				logger.log(Level.WARNING, "Failed to compile input: " + input, e);
				return "int main(){return 0;}";
			}
		}
	}

	private static String readContent(File file) {
		try {
			return file.readString();
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to read content from: " + file, e);
			return "";
		}
	}
}
