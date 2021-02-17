package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		try {
			var source = Paths.get(".", "Main.mg");
			var sourceString = Files.readString(source);
			var lines = new ArrayList<String>();
			var buffer = new StringBuilder();
			var depth = 1;
			for (int i = 0; i < sourceString.length(); i++) {
				var c = sourceString.charAt(i);
				if (c == '}' && depth == 1) {
					buffer.append('}');
					lines.add(buffer.toString());
					buffer = new StringBuilder();
					depth -= 1;
				} else if (c == ';' && depth == 0) {
					lines.add(buffer.toString());
					buffer = new StringBuilder();
				} else {
					if (c == '{') depth++;
					if (c == '}') depth--;
					buffer.append(c);
				}
			}
			lines.add(buffer.toString());
			for (String line : lines) {
				var colon = line.indexOf(':');
				var value = line.indexOf('=');

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
