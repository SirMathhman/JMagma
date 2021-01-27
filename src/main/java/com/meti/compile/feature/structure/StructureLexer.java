package com.meti.compile.feature.structure;

import com.meti.compile.Compiler;
import com.meti.compile.MagmaCompiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class StructureLexer implements Lexer {
	public static final StructureLexer StructureLexer_ = new StructureLexer();

	private StructureLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("struct ") && line.contains("{") && line.endsWith("}");
	}

	@Override
	public Token lex(String line) {
		var separator = line.indexOf('{');
		var nameSlice = line.substring(7, separator);
		var name = nameSlice.trim();
		var membersSlice = line.substring(separator + 1, line.length() - 1);
		var membersString = membersSlice.trim();
		var members = MagmaCompiler.splitSequence(membersString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(line1 -> MagmaLexingStage_.lexField(line1))
				.collect(Collectors.toList());
		var renderedMembers = members.stream()
				.map(value -> value + ";")
				.collect(Collectors.joining("", "{", "}"));
		return new Content("struct " + name + renderedMembers + ";");
	}
}