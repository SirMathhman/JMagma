package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequences;
import com.meti.api.magma.collect.StreamException;
import com.meti.api.magma.core.Option;
import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.EmptyField;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.api.magma.core.Some.Some;
import static com.meti.compile.token.FieldLexer.FieldLexer_;

public class FunctionNodeLexer_ implements Lexer<Token> {
	public static final Lexer<Token> FunctionNodeLexer_ = new FunctionNodeLexer_();

	private FunctionNodeLexer_() {
	}

	@Override
	public Option<Token> lex(String content) throws CompileException {
		return Some(content)
				.filter(this::isValid)
				.mapE1(this::lexValid);
	}

	private Implementation lexValid(String content) throws CompileException {
		var keySlice = content.substring(0, content.indexOf('('));
		var keyString = keySlice.trim();
		var space = keyString.lastIndexOf(' ');
		var flags = lexFlags(keyString, space);
		var name = lexName(keyString, space);
		var parameters = lexParameters(content);
		var returnType = lexReturn(content);
		var value = lexValue(content);
		Token type = createFunctionType(parameters, returnType);
		var identity = new EmptyField(flags, name, type);
		return new Implementation(identity, parameters, value);
	}

	private Token createFunctionType(ArrayList<Field> parameters, Content returnType) {
		try {
			return Sequences.stream(JavaLists.fromJava(parameters.stream()
				.map(Field::findType)
				.collect(Collectors.toList())))
					.fold(FunctionType.Empty, FunctionType.WithoutReturn::withParameter)
					.withReturn(returnType).complete();
		} catch (StreamException e) {
			return FunctionType.Empty.withReturn(returnType)
					.complete();
		}
	}

	private Content lexValue(String content) {
		var valueSlice = content.substring(content.indexOf("=>") + 2);
		var valueString = valueSlice.trim();
		var value = new Content(valueString);
		return value;
	}

	private Content lexReturn(String content) {
		var returnSlice = content.substring(content.indexOf(':') + 1, content.indexOf("=>"));
		var returnString = returnSlice.trim();
		var returnType = new Content(returnString);
		return returnType;
	}

	private ArrayList<Field> lexParameters(String content) throws CompileException {
		var paramSlice = content.substring(content.indexOf('(') + 1, content.indexOf(')'));
		var paramString = paramSlice.trim();
		var paramStrings = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		var paramLength = paramString.length();
		for (int i = 0; i < paramLength; i++) {
			var c = paramString.charAt(i);
			if (c == ',' && depth == 0) {
				paramStrings.add(buffer.toString());
				buffer = new StringBuilder();
			} else {
				if (c == '(') depth++;
				if (c == ')') depth--;
				buffer.append(c);
			}
		}
		paramStrings.add(buffer.toString());
		paramStrings.removeIf(String::isBlank);

		var parameters = new ArrayList<Field>();
		for (String string : paramStrings) {
			parameters.add(FieldLexer_.lex(string).orElseThrow(() -> new CompileException("Invalid field: " + string)));
		}
		return parameters;
	}

	private List<Field.Flag> lexFlags(String keyString, int space) {
		var flagSlice = keyString.substring(0, space);
		var flagString = flagSlice.trim();
		var flags = Arrays.stream(flagString.split(" "))
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(String::toUpperCase)
				.map(Field.Flag::valueOf)
				.collect(Collectors.toList());
		return flags;
	}

	private String lexName(String keyString, int space) {
		var nameSlice = keyString.substring(space + 1);
		return nameSlice.trim();
	}

	private boolean isValid(String content) {
		return content.contains("(") &&
		       content.contains(")") &&
		       content.contains(":") &&
		       content.contains("=>");
	}
}
