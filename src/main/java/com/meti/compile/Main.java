package com.meti.compile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	private static final Compiler Compiler = new Compiler();

	public static void main(String[] args) {
		ensureInputPath();
		var input = readContent(Paths.get(".", "Main.mg"));
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
		if (!Files.exists(Paths.get(".", "Main.c"))) {
			try {
				Files.createFile(Paths.get(".", "Main.c"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String compile(String input) {
		String output;
		if (input.isBlank()) {
			output = "int main(){return 0;}";
		} else {
			try {
				output = Compiler.compile(input);
			} catch (CompileException e) {
				output = "int main(){return 0;}";
			}
		}
		return output;
	}

	private static void ensureInputPath() {
		if (!Files.exists(Paths.get(".", "Main.mg"))) {
			try {
				Files.createFile(Paths.get(".", "Main.mg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String readContent(java.nio.file.Path input) {
		try {
			return Files.readString(input);
		} catch (IOException e) {
			return "";
		}
	}
}
