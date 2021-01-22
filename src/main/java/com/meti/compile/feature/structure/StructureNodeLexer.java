package com.meti.compile.feature.structure;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Optional;

import static com.meti.compile.token.FieldLexer.FieldLexer_;

public class StructureNodeLexer implements Lexer<Token> {
	public static final Lexer<Token> StructureNodeLexer_ = new StructureNodeLexer();

	public StructureNodeLexer() {
	}

	@Override
	public Option<Token> lex(String content) throws CompileException {
		return lex1(content).map(Some::Some).orElseGet(None::None);
	}

	private Optional<Token> lex1(String content) throws CompileException {
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

			var members = new ArrayList<Field>();
			for (String memberString : memberStrings) {
				if (!memberString.isBlank()) {
					var option = FieldLexer_.lex(memberString.trim()).map(Optional::of).orElseGet(Optional::empty);
					option.ifPresent(members::add);
				}
			}
			return Optional.of(new Structure(name, members));
		}
		return Optional.empty();
	}
}
