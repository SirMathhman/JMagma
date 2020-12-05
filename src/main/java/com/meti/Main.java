package com.meti;

import com.meti.api.log.Logger;
import com.meti.api.log.OutputStreamLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.meti.api.log.Logger.Level.Error;
import static com.meti.api.log.Logger.Level.Warning;

public class Main {
	private static final Logger LOGGER = new OutputStreamLogger(System.err);

	public static void main(String[] args) {
		var path = Paths.get("Main.mgs");
		if (!Files.exists(path)) {
			LOGGER.logSimple(Error, "Main file doesn't exist.");
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
					LOGGER.logSimple(Warning, message);
				}
			} catch (IOException e) {
				LOGGER.logExceptionally(Error, "Cannot open main file.", e);
			}
		}
	}

	enum Group {
		Target
	}
}
