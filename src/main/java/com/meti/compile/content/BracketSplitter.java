package com.meti.compile.content;

import java.util.ArrayList;
import java.util.List;

public class BracketSplitter {
	public static final BracketSplitter BracketSplitter_ = new BracketSplitter();

	private BracketSplitter() {
	}

	public List<String> split(String content) {
		var lines = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < content.length(); i++) {
			var c = content.charAt(i);
			if (c == '}' && depth == 1) {
				depth = 0;
				buffer.append('}');
				lines.add(buffer.toString());
				buffer = new StringBuilder();
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
		lines.removeIf(String::isBlank);
		return lines;
	}
}