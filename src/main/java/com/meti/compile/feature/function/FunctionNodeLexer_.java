package com.meti.compile.feature.function;

import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.EmptyField;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.meti.compile.token.FieldLexer.FieldLexer_;

public class FunctionNodeLexer_ implements Lexer<Token> {
	public static final Lexer<Token> FunctionNodeLexer_ = new FunctionNodeLexer_();

	public FunctionNodeLexer_() {
	}

	@Override
	public Optional<Token> lex(String content) throws CompileException {
		if (content.contains("(") &&
		    content.contains(")") &&
		    content.contains(":") &&
		    content.contains("=>")) {
			var paramStart = content.indexOf('(');
			var paramEnd = content.indexOf(')');
			var typeSeparator = content.indexOf(':');
			var valueSeparator = content.indexOf("=>");

			var keySlice = content.substring(0, paramStart);
			var keyString = keySlice.trim();
			var space = keyString.lastIndexOf(' ');
			var flagSlice = keyString.substring(0, space);
			var flagString = flagSlice.trim();
			var flags = Arrays.stream(flagString.split(" "))
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(String::toUpperCase)
					.map(Field.Flag::valueOf)
					.collect(Collectors.toList());
			var nameSlice = keyString.substring(space + 1);
			var name = nameSlice.trim();

			var paramSlice = content.substring(paramStart + 1, paramEnd);
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
				var option = FieldLexer_.lex(string);
				var field = option.orElseThrow(() -> new CompileException("Invalid field: " + string));
				parameters.add(field);
			}

			var returnSlice = content.substring(typeSeparator + 1, valueSeparator);
			var returnString = returnSlice.trim();
			var returnType = new Content(returnString);

			var valueSlice = content.substring(valueSeparator + 2);
			var valueString = valueSlice.trim();
			var value = new Content(valueString);

			var parameterTypes = parameters.stream()
					.map(Field::findType)
					.collect(Collectors.toList());
			var type = new FunctionType(returnType, parameterTypes);
			var identity = new EmptyField(flags, name, type);
			return Optional.of(new Implementation(identity, parameters, value));
		}
		return Optional.empty();
	}
}
