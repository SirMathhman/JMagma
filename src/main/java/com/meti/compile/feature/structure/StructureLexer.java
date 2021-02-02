package com.meti.compile.feature.structure;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.content.ParameterSplitter;
import com.meti.compile.token.Input;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.meti.compile.lex.MagmaLexingStage.MagmaLexingStage_;

public class StructureLexer implements Lexer<Token> {
	public static final StructureLexer StructureLexer_ = new StructureLexer();

	private StructureLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("struct ") && content.contains("{") && content.endsWith("}");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? Some.Some(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var separator = line.indexOf('{');
		var nameSlice = line.substring(7, separator);
		var name = nameSlice.trim();
		var membersSlice = line.substring(separator + 1, line.length() - 1);
		var membersString = membersSlice.trim();
		ArrayList<String> members = null;
		try {
			members = ParameterSplitter.ParameterSplitter_.stream(new Input(membersString)).map(Input::getContent)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(line1 -> MagmaLexingStage_.lexField(new Input(line1)).render().asString())
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