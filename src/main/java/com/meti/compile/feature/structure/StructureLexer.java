package com.meti.compile.feature.structure;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.content.ParameterSplitter;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class StructureLexer implements Lexer<Token> {
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
		ArrayList<String> members = null;
		try {
			members = ParameterSplitter.ParameterSplitter_.stream(membersString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(line1 -> MagmaLexingStage_.lexField(line1).render())
					.fold(new ArrayList<String>(), JavaLists::add);
		} catch (StreamException e) {
			members = new ArrayList<>();
		}
		var renderedMembers = members.stream()
				.map(value -> value + ";")
				.collect(Collectors.joining("", "{", "}"));
		return new Content("struct " + name + renderedMembers + ";");
	}
}