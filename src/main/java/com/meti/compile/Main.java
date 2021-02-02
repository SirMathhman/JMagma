package com.meti.compile;

import com.meti.compile.token.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;

public class Main {
	public static void main(String[] args) {
		try {
			var path = Paths.get(".", "Main.mg");
			var content = Files.readString(path);
			var result = MagmaCompiler_.compile(new Input(content));
			var joinedOutput = result.getValue();
			var target = Paths.get(".", "Main.c");
			Files.writeString(target, joinedOutput);
		} catch (IOException | CompileException e) {
			e.printStackTrace();
		}
	}
}
