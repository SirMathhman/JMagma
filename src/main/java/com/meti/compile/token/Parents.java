package com.meti.compile.token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parents {
	public Parents() {
	}

	public static Formatter format(String format) {
		return new Formatter(format, Collections.emptyList());
	}

	public static Token join(String delimiter, List<Token> tokens) {
		if (tokens.isEmpty()) return new Parent(Collections.emptyList());
		var lines = new ArrayList<Token>();
		var first = tokens.get(0);
		lines.add(first);
		for (int i = 1; i < tokens.size(); i++) {
			lines.add(new Content(delimiter));
			lines.add(tokens.get(i));
		}
		return new Parent(tokens);
	}

	public static record Formatter(String format, List<Token> lines) {
		public Token complete() {
			return new Parent(lines);
		}

		public Formatter format(Token token) {
			var index = format.indexOf("%t");
			if (index == -1) return new Formatter(format, lines);
			else {
				var before = format.substring(0, index);
				var after = format.substring(index + 2);
				var newLines = new ArrayList<>(lines);
				newLines.add(new Content(before));
				newLines.add(token);
				return new Formatter(after, newLines);
			}
		}
	}
}
