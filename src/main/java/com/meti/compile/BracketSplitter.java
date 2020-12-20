package com.meti.compile;

import java.util.ArrayList;

public class BracketSplitter implements Splitter<ArrayList<String>> {
	public static final BracketSplitter BracketSplitter_ = new BracketSplitter();

	private BracketSplitter() {
	}

	@Override
	public ArrayList<String> split(String content) {
		var list = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < content.length(); i++) {
			var c = content.charAt(i);
			if (c == '}' && depth == 1) {
				buffer.append('}');
				depth--;
				list.add(buffer.toString());
				buffer = new StringBuilder();
			} else if (c == ';' && depth == 0) {
				list.add(buffer.toString());
				buffer = new StringBuilder();
			} else {
				if (c == '{') {
					depth++;
				} else if (c == '}') {
					depth--;
				}
				buffer.append(c);
			}
		}
		list.add(buffer.toString());
		list.removeIf(String::isBlank);
		return list;
	}
}