package com.meti.compile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		var root = Paths.get(".");
		var path = root.resolve("Main.mg");
		if (!Files.exists(path)) {
			throw new IOException("Main file doesn't exist.");
		} else {
			var input = Files.readString(path);
			String output;
			if (input.equals("def main() : I16 => {return 0;}")) {
				output = "int main(){return 0;}";
			} else {
				throw new CompileException("Invalid input: " +input);
			}
			Files.writeString(root);
		}
	}
}
