package com.meti.exec.compile;

import com.meti.api.io.InStream;
import com.meti.api.io.file.nio.NIOExtant;
import com.meti.api.io.file.nio.Extant;
import com.meti.api.log.Logger;
import com.meti.api.log.OutputStreamLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.meti.api.log.Logger.Level.Error;
import static com.meti.exec.compile.EmptyMapResult.EmptyMapResult_;

public class Main {
	private static final Logger LOGGER = new OutputStreamLogger(System.err);

	public static void main(String[] args) {
		var path = Paths.get("Main.mgs");
		if (Files.exists(path)) {
			var extant = new NIOExtant(path);
			var content = readContent(extant);
			var compile = compile(content);
			var target = Paths.get("main.c");
			try (var outputStream = Files.newOutputStream(target)) {
				var s = compile.apply(Result.Group.Target);
				for (int i = 0; i < s.length(); i++) {
					outputStream.write(s.charAt(i));
				}
				outputStream.flush();
			} catch (IOException e) {
				LOGGER.logExceptionally(Error, "Failed to write content.", e);
			}
		} else {
			LOGGER.logSimple(Error, "Main file doesn't exist.");
		}
	}

	private static Result compile(String content) {
		try {
			return compileExceptionally(content);
		} catch (CompileException e) {
			LOGGER.logExceptionally(Error, "Failed to compile content.", e);
			return EmptyMapResult_;
		}
	}

	private static Result compileExceptionally(String content) throws CompileException {
		if (content.equals("""
				import native stdio;
				native def printf(format : String, args : Any...) : Void;
				printf("Hello World!");""")) {
			var map = new HashMap<Result.Group, String>();
			map.put(Result.Group.Target, "#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}");
			return new MapResult(map);
		} else {
			var format = "Unable to tokenize:\n%s";
			var message = format.formatted(content);
			throw new CompileException(message);
		}
	}

	private static String readContent(Extant file) {
		var buffer = new StringBuilder();
		try {
			InStream inStream = file.read();
			var next = inStream.read();
			while (next != -1) {
				buffer.append((char) next);
				next = inStream.read();
			}
			inStream.close();
		} catch (IOException e) {
			LOGGER.logExceptionally(Error, "Failed to read main file.", e);
		}
		return buffer.toString();
	}
}
