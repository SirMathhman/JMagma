package com.meti.compile;

import com.meti.api.io.NIOFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static com.meti.api.io.NIOPath.Root;

public class Main {
	private static final Compiler Compiler = new Compiler();

	public static void main(String[] args) {
		var input = ensureInputPath()
				.map(Main::readContent)
				.orElse("");
		var output = compile(input);
		ensureIntermediatePath();
		writeIntermediate(output);
		compileIntermediate();
		deleteIntermediate();
	}

	private static void deleteIntermediate() {
		try {
			Files.deleteIfExists(Paths.get(".", "Main.c"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void compileIntermediate() {
		try {
			var builder = new ProcessBuilder("gcc", "-o", "Main", Paths.get(".", "Main.c").toAbsolutePath().toString());
			var start = builder.start();
			start.getInputStream().transferTo(System.out);
			start.getErrorStream().transferTo(System.err);
			start.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void writeIntermediate(String output) {
		try {
			Files.writeString(Paths.get(".", "Main.c"), output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ensureIntermediatePath() {
		if (Root.resolve("Main.c").doesNotExist()) {
			try {
				Files.createFile(Paths.get(".", "Main.c"));
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	private static Optional<NIOFile> ensureInputPath() {
		try {
			return Optional.of(Root.resolve("Main.mg").ensureAsFile());
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	private static String readContent(NIOFile nioFile) {
		try {
			return Files.readString(nioFile.getValue());
		} catch (IOException e) {
			return "";
		}
	}
}
