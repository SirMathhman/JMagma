package com.meti.compile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		var input = Paths.get(".", "Main.mg");
		if (!Files.exists(input)) {
			try {
				Files.createFile(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String content;
		try {
			content = Files.readString(input);
		} catch (IOException e) {
			content = "";
		}
		String output;
		if (content.isBlank()) {
			output = "int main(){return 0;}";
		} else {
			output = "int main(){return 0;}";
		}
		var outputFile = Paths.get(".", "Main.c");
		if (!Files.exists(outputFile)) {
			try {
				Files.createFile(outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Files.writeString(outputFile, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			var builder = new ProcessBuilder("gcc", "-o", "Main", "Main.c");
			var start = builder.start();
			start.getInputStream().transferTo(System.out);
			start.getErrorStream().transferTo(System.err);
			start.waitFor();

			Files.deleteIfExists(outputFile);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
