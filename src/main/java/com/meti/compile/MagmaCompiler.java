package com.meti.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	public static Stream<String> splitSequence(String sequence) {
		return Arrays.stream(sequence.split(","));
	}

	@Override
	public String compile(String content) {
		return split(content).stream()
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(line -> MagmaLexingStage_.lexNode(line, this).getValue())
				.collect(Collectors.joining());
	}

	private static List<String> split(String content) {
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