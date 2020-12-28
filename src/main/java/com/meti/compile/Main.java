package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.io.NIOFileSystem.NIOFileSystem_;

public class Main {
	private static final Logger logger = Logger.getAnonymousLogger();

	public static void main(String[] args) {
		ensureSourceDirectory()
				.map(DirectorySource::new)
				.ifPresent(Main::runWithSource);
	}

	private static void runWithSource(Source source) {
		ensureTargetDirectory()
				.map(directory -> new DirectoryTarget<CClass>(directory))
				.ifPresent(target -> runWithBoth(source, target));
	}

	private static void runWithBoth(Source source, Target<CClass, File> target) {
		try {
			var intermediates = MagmaCompiler.MagmaCompiler_.compile(source, target);
			if (intermediates.isEmpty()) {
				logger.log(Level.WARNING, "No intermediate files are present to compile.");
			} else {
				compileIntermediates(intermediates);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to compile target.", e);
		} catch (CompileException e) {
			logger.log(Level.SEVERE, "Failed to compile content.", e);
		}
	}

	private static Option<Directory> ensureTargetDirectory() {
		try {
			return Some(NIOFileSystem_.Root()
					.resolve("target")
					.ensureDirectory()
					.deleteChildren());
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to ensure target directory.", e);
			return None();
		}
	}

	private static Option<Directory> ensureSourceDirectory() {
		var directory = NIOFileSystem_.Root().resolve("source");
		try {
			return Some(directory.ensureDirectory());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to ensure soure directory at: ", e);
			return None();
		}
	}

	private static void compileIntermediates(List<File> intermediates) {
		try {
			compileIntermediatesExceptionally(intermediates);
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Failed to compile intermediate files.", e);
		}
	}

	private static void compileIntermediatesExceptionally(List<File> intermediates) throws IOException, InterruptedException {
		var command = buildCommand(intermediates);
		var builder = new ProcessBuilder(command);
		var start = builder.start();
		start.getInputStream().transferTo(System.out);
		start.getErrorStream().transferTo(System.err);
		start.waitFor();
	}

	private static ArrayList<String> buildCommand(List<File> intermediates) {
		var command = new ArrayList<>(List.of("gcc", "-o", "Main"));
		command.addAll(intermediates.stream()
				.map(File::asPath)
				.map(Path::asAbsolute)
				.map(Path::toString)
				.collect(Collectors.toList()));
		return command;
	}
}
