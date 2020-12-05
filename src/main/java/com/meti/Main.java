package com.meti;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		var path = Paths.get("Main.mgs");
		if (!Files.exists(path)) {
			logSimple(Level.Error, "Main file doesn't exist.");
		} else {
			try {
				var inputStream = Files.newInputStream(path);
				var next = inputStream.read();
				var buffer = new StringBuilder();
				while (next != -1) {
					buffer.append((char) next);
					next = inputStream.read();
				}
				var content = buffer.toString();
				var map = new HashMap<Group, String>();
				map.put(Group.Target, "#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}");
				if (content.equals("""
						import native stdio;
						native def printf(format : String, args : Any...) : Void;
						printf("Hello World!");""")) {
					var target = Paths.get("main.c");
					try (var outputStream = Files.newOutputStream(target)) {
						var s = map.get(Group.Target);
						for (int i = 0; i < s.length(); i++) {
							outputStream.write(s.charAt(i));
						}
						outputStream.flush();
					}
				} else {
					var format = "Unable to compile content:\n%s";
					var message = format.formatted(content);
					logSimple(Level.Warning, message);
				}
			} catch (IOException e) {
				logExceptionally(Level.Error, "Cannot open main file.", e);
			}
		}
	}

	private static void logSimple(Level level, final String message) {
		System.out.printf("[%s] %s%n", level, message);
	}

	private static void logExceptionally(Level level, String message, Exception e) {
		var parent = new StringWriter();
		var child = new PrintWriter(parent);
		e.printStackTrace(child);
		var parentString = parent.toString();
		var formatted = "%s: %s".formatted(message, parentString);
		logSimple(level, formatted);
	}

	enum Group {
		Target
	}

	enum Level {
		Warning,
		Error
	}
}
