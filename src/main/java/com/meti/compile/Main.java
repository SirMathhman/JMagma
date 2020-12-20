package com.meti.compile;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.api.core.Option;
import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.io.NIOFileSystem.NIOFileSystem_;

public class Main {
	private static final Compiler Compiler = new Compiler();

	public static void main(String[] args) {
		List<Path> children;
		try {
			children = NIOFileSystem_.Root()
					.resolve("source")
					.ensuringAsDirectory()
					.walk();
		} catch (IOException e) {
			e.printStackTrace();
			children = Collections.emptyList();
		}
		var intermediates = new ArrayList<Path>();
		for (Path child : children) {
			var name = child.name();
			if (child.hasExtensionOf("mg")) {
				try {
					var file = child.ensureAsFile();
					var input = readContent(file);
					var output = compile(input);
					var intermediate = child.resolveSibling(name, "c");
					ensureIntermediatePath(intermediate)
							.map(file1 -> writeIntermediate(file1, output))
							.ifPresent(intermediates::add);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		compileIntermediates(intermediates);
		for (Path intermediate : intermediates) {
			deleteIntermediate(intermediate);
		}
	}

	private static Path writeIntermediate(File file, String output) {
		try {
			return file.writeString(output);
		} catch (IOException e) {
			e.printStackTrace();
			return file.asPath();
		}
	}

	private static void deleteIntermediate(Path resolve) {
		try {
			resolve.existing().ifPresentExceptionally(File::delete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void compileIntermediates(List<Path> intermediates) {
		try {
			var command = new ArrayList<>(List.of("gcc", "-o", "Main"));
			command.addAll(intermediates.stream()
					.map(Path::asAbsolute)
					.map(Path::toString)
					.collect(Collectors.toList()));
			var builder = new ProcessBuilder(command);
			var start = builder.start();
			start.getInputStream().transferTo(System.out);
			start.getErrorStream().transferTo(System.err);
			start.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Option<File> ensureIntermediatePath(Path intermediate) {
		try {
			return Some(intermediate.ensureAsFile());
		} catch (IOException e) {
			e.printStackTrace();
			return None();
		}
	}

	private static String compile(String input) {
		if (input.isBlank()) {
			return "int main(){return 0;}";
		} else {
			try {
				return Compiler.compile(input);
			} catch (CompileException e) {
				e.printStackTrace();
				return "int main(){return 0;}";
			}
		}
	}

	private static String readContent(File file) {
		try {
			return file.readString();
		} catch (IOException e) {
			return "";
		}
	}

}
