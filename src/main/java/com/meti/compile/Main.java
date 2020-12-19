package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.api.io.File;
import com.meti.api.io.Path;

import java.io.IOException;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.io.NIOFileSystem.NIOFileSystem_;

public class Main {
	private static final Path Root = NIOFileSystem_.Root();
	private static final Path Intermediate = Root.resolve("Main.c");
	private static final Path Source = NIOFileSystem_.Root().resolve("Main.mg");
	private static final Compiler Compiler = new Compiler();

	public static void main(String[] args) {
		ensureIntermediatePath()
				.map(Main::writeIntermediate)
				.map(Main::compileIntermediate)
				.ifPresent(Main::deleteIntermediate);
	}

	private static Path writeIntermediate(File file) {
		try {
			return file.writeString(compileOutput());
		} catch (IOException e) {
			e.printStackTrace();
			return file.asPath();
		}
	}

	private static String compileOutput() {
		return ensureInputPath()
				.map(Main::readContent)
				.map(Main::compile)
				.orElse("");
	}

	private static void deleteIntermediate(Path resolve) {
		try {
			resolve.existing().ifPresentExceptionally(File::delete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Path compileIntermediate(Path resolve) {
		try {
			var intermediateString = resolve.asAbsolute().toString();
			var builder = new ProcessBuilder("gcc", "-o", "Main", intermediateString);
			var start = builder.start();
			start.getInputStream().transferTo(System.out);
			start.getErrorStream().transferTo(System.err);
			start.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return resolve;
	}

	private static Option<File> ensureIntermediatePath() {
		try {
			return Some(Intermediate.ensureAsFile());
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

	private static Option<File> ensureInputPath() {
		try {
			return Some(Source.ensureAsFile());
		} catch (IOException e) {
			e.printStackTrace();
			return None();
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
