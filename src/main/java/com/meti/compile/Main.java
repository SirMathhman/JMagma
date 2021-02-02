package com.meti.compile;

import com.meti.compile.token.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		try {
			var path = Paths.get(".", "Main.mg");
			var content = Files.readString(path);
			var joinedOutput = MagmaCompiler.MagmaCompiler_.compile(new Input(content)).getValue();
			var target = Paths.get(".", "Main.c");
			Files.writeString(target, joinedOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	enum Flag {
		LET,
		CONST
	}
}
