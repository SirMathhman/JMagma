package com.meti.compile.feature.function;

import com.meti.compile.Compiler;
import com.meti.compile.MagmaCompiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class FunctionLexer implements Lexer {
	public static final Lexer FunctionLexer_ = new FunctionLexer();

	private FunctionLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("def");
	}

	@Override
	public Token lex(String line) {
		var paramStart = line.indexOf('(');
		var paramEnd = line.indexOf(')');
		var returnsSeparator = line.indexOf(":");
		var bodySeparator = line.indexOf("=>");
		var keysSlice = line.substring(0, paramStart);
		var keysString = keysSlice.trim();
		var space = keysString.lastIndexOf(' ');
		var nameSlice = keysString.substring(space + 1);
		var name = nameSlice.trim();
		var paramSlice = line.substring(paramStart + 1, paramEnd);
		var paramString = paramSlice.trim();
		var parameters = MagmaCompiler.splitSequence(paramString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(line1 -> MagmaLexingStage_.lexField(line1))
				.collect(Collectors.toList());
		var typeSlice = line.substring(returnsSeparator + 1, bodySeparator);
		var typeString = typeSlice.trim();
		var type = MagmaLexingStage_.lexType(typeString);

		var bodySlice = line.substring(bodySeparator + 2);
		var bodyString = bodySlice.trim();
		var body = MagmaLexingStage_.lexNode(bodyString).render();

		var renderedParameters = parameters.stream().collect(Collectors.joining(",", "(", ")"));
		return new Content("%s %s%s%s".formatted(type, name, renderedParameters, body));
	}
}