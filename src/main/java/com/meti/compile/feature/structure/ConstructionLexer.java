package com.meti.compile.feature.structure;

import com.meti.compile.Compiler;
import com.meti.compile.MagmaCompiler;
import com.meti.compile.feature.scope.Lexer;

import java.util.stream.Collectors;

public class ConstructionLexer implements Lexer {
	public static final ConstructionLexer ConstructionLexer_ = new ConstructionLexer();

	private ConstructionLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("[") && line.contains("]") &&
		       line.contains("{") && line.contains("}");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		var bodyStart = line.indexOf('{');
		var bodySlice = line.substring(bodyStart + 1, line.length() - 1);
		var bodyString = bodySlice.trim();
		var arguments = MagmaCompiler.splitSequence(bodyString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(compiler::compileNode)
				.collect(Collectors.toList());
		return arguments.stream().collect(Collectors.joining(",", "{", "}"));
	}
}