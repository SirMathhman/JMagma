package com.meti.compile.feature.structure;

import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.meti.compile.token.FieldLexer.FieldLexer_;

public class StructureNodeLexer implements Lexer<Token> {
	public static final Lexer<Token> StructureNodeLexer_ = new StructureNodeLexer();

	public StructureNodeLexer() {
	}

	@Override
	public Optional<Token> lex(String content) {
		if (content.startsWith("struct ") &&
		    content.contains("{") &&
		    content.endsWith("}")) {

			var separator = content.indexOf('{');
			var nameSlice = content.substring(7, separator);
			var name = nameSlice.trim();

			var parameterSlice = content.substring(separator + 1, content.length() - 1);
			var parameterString = parameterSlice.trim();
			var memberStrings = new ArrayList<String>();
			var buffer = new StringBuilder();
			var depth = 0;
			var length = parameterString.length();
			for (int i = 0; i < length; i++) {
				var c = parameterString.charAt(i);
				if (c == ',' && depth == 0) {
					memberStrings.add(buffer.toString());
					buffer = new StringBuilder();
				} else {
					if (c == '(') depth++;
					if (c == ')') depth--;
					buffer.append(c);
				}
			}
			memberStrings.add(buffer.toString());
			memberStrings.removeIf(String::isBlank);

			var members = memberStrings.stream()
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(FieldLexer_::lex)
					.flatMap(Optional::stream)
					.collect(Collectors.toList());
			return Optional.of(new Structure(name, members));
		}
		return Optional.empty();
	}
}
