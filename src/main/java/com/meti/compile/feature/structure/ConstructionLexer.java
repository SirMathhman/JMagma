package com.meti.compile.feature.structure;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.content.ParameterSplitter;
import com.meti.compile.feature.scope.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ConstructionLexer implements Lexer<Token> {
	public static final ConstructionLexer ConstructionLexer_ = new ConstructionLexer();

	private ConstructionLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("[") && content.contains("]") &&
		       content.contains("{") && content.contains("}");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? new Some<>(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var bodyStart = line.indexOf('{');
		var bodySlice = line.substring(bodyStart + 1, line.length() - 1);
		var bodyString = bodySlice.trim();
		ArrayList<String> arguments = null;
		try {
			arguments = (ParameterSplitter.ParameterSplitter_.stream(bodyString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(line1 -> MagmaLexingStage_.lexNode(line1).render())
					.fold(new ArrayList<String>(), JavaLists::add));
		} catch (StreamException e) {
			arguments = new ArrayList<>();
		}
		return new Content(arguments.stream().collect(Collectors.joining(",", "{", "}")));
	}
}