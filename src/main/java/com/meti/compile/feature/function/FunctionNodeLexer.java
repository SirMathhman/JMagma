package com.meti.compile.feature.function;

import com.meti.api.magma.collect.*;
import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.Option;
import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Field;
import com.meti.compile.token.Fields;
import com.meti.compile.token.Token;

import static com.meti.api.magma.core.Some.Some;
import static com.meti.compile.content.ParameterSplitter.ParameterSplitter_;
import static com.meti.compile.token.FieldLexer.FieldLexer_;

public class FunctionNodeLexer implements Lexer<Token> {
	public static final Lexer<Token> FunctionNodeLexer_ = new FunctionNodeLexer();

	private FunctionNodeLexer() {
	}

	private Implementation.Both attachBody(Implementation.WithIdentity builder, String content) {
		var separator = findBody(content);
		var valueSlice = content.substring(separator + 2);
		var valueString = valueSlice.trim();
		var body = new Content(valueString);
		return builder.withBody(body);
	}

	private Fields.Neither attachFlags(String content) throws StreamException {
		var keys = lexKeys(content);
		var separator = keys.lastIndexOf(' ');
		var flagSlice = keys.substring(0, separator);
		var flagString = flagSlice.trim();
		var flagStrings = flagString.split(" ");
		return foldFlags(flagStrings);
	}

	private Implementation.WithIdentity attachIdentity(Implementation.Neither builder, List<Field> parameters, String content) throws CompileException {
		try {
			var withFlags = attachFlags(content);
			var withName = attachName(withFlags, content);
			var withType = attachType(withName, content, parameters);
			var identity = withType.complete();
			return builder.withIdentity(identity);
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}

	private Fields.WithName attachName(Fields.Neither builder, String content) {
		var keys = lexKeys(content);
		var separator = keys.lastIndexOf(' ');
		var nameSlice = keys.substring(separator + 1);
		var name = nameSlice.trim();
		return builder.withName(name);
	}

	private List<Field> attachParameters(String paramsString) {
		var lexParameter = (F1E1<String, Field, CompileException>) paramString -> FieldLexer_
				.lex(paramString)
				.orElseThrow(() -> new CompileException("Invalid field: " + paramString));
		try {
			return ParameterSplitter_.stream(paramsString)
					.map(lexParameter)
					.fold(ArrayLists.empty(), List::add);
		} catch (CollectionException e) {
			return ArrayLists.empty();
		}
	}

	private Fields.Both attachType(Fields.WithName builder, String content, List<Field> parameters) throws StreamException {
		return builder.withType(foldIntoType(content, parameters));
	}

	private int findBody(String content) {
		return content.indexOf("=>");
	}

	private int findParamEnd(String content) {
		return content.indexOf(')');
	}

	private int findParamStart(String content) {
		return content.indexOf('(');
	}

	private int findReturn(String content) {
		return content.indexOf(':');
	}

	private Fields.Neither foldFlags(String[] flags) throws StreamException {
		return Streams.ofArray(flags)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(String::trim)
				.map(String::toUpperCase)
				.map(Field.Flag::valueOf)
				.fold(Fields.Empty, Fields.Neither::withFlag);
	}

	private Token foldIntoType(String content, List<Field> parameters) throws StreamException {
		return Sequences.stream(parameters)
				.map(Field::findType)
				.fold(FunctionType.Empty, FunctionType.WithoutReturn::withParameter)
				.withReturn(lexReturn(content))
				.complete();
	}

	private Implementation.Neither fromParameters(List<Field> parameters) throws CompileException, StreamException {
		return Sequences.stream(parameters).fold(Implementation.Empty, Implementation.Neither::withParameter);
	}

	private boolean isValid(String content) {
		return content.contains("(") &&
		       content.contains(")") &&
		       content.contains(":") &&
		       content.contains("=>");
	}

	@Override
	public Option<Token> lex(String content) throws CompileException {
		return Some(content)
				.filter(this::isValid)
				.mapE1(this::lexImpl);
	}

	private Token lexExceptionally(String content) throws CompileException, StreamException {
		var parameters = lexParameters(content);
		var withParameters = fromParameters(parameters);
		var withIdentity = attachIdentity(withParameters, parameters, content);
		var withBody = attachBody(withIdentity, content);
		return withBody.complete();
	}

	private Token lexImpl(String content) throws CompileException {
		try {
			return lexExceptionally(content);
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}

	private String lexKeys(String content) {
		var paramStart = findParamStart(content);
		var keySlice = content.substring(0, paramStart);
		return keySlice.trim();
	}

	private List<Field> lexParameters(String content) throws StreamException {
		var start = findParamStart(content);
		var end = findParamEnd(content);
		var paramSlice = content.substring(start + 1, end);
		var paramString = paramSlice.trim();
		return attachParameters(paramString);
	}

	private Token lexReturn(String content) {
		var returns = findReturn(content);
		var body = findBody(content);
		var returnSlice = content.substring(returns + 1, body);
		var returnString = returnSlice.trim();
		return new Content(returnString);
	}
}
