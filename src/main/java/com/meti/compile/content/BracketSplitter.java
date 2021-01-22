package com.meti.compile.content;

import com.meti.api.java.collect.JavaList;
import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.collect.Stream;

import java.util.ArrayList;

public class BracketSplitter implements Splitter {
	public static final BracketSplitter BracketSplitter_ = new BracketSplitter();

	private BracketSplitter() {
	}

	@Override
	public Stream<String> stream(String content) {
		var lines = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int j = 0; j < content.length(); j++) {
			var c = content.charAt(j);
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
		return Lists.stream(new JavaList<>(lines));
	}
}