package com.meti.compile.feature.function;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.Type;
import com.meti.compile.feature.field.Field;

import java.util.*;
import java.util.stream.Collectors;

import static com.meti.compile.TokenizationException.TokenizationException;
import static com.meti.compile.feature.ImplicitType.ImplicitType_;
import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.field.FieldTokenizer.FieldTokenizer_;

public class FunctionTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> FunctionTokenizer_ = new FunctionTokenizer();

	public FunctionTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) throws TokenizationException {
		if (content.contains("(") && content.contains(")")) {
			var paramStart = content.indexOf('(');
			var paramEnd = content.indexOf(')');
			var headerSlice = content.substring(0, paramStart);
			var header = headerSlice.trim();
			var builder = tokenizeHeader(header);
			var hasValue = content.contains("=>");
			if (builder.flags().contains(Field.Flag.DEF) || (hasValue && paramEnd < content.indexOf("=>"))) {
				var paramSlice = content.substring(paramStart + 1, paramEnd);
				var paramString = paramSlice.trim();
				var parameters = tokenizeParameters(paramString);
				var withoutReturn = builder.withParameters(parameters);
				var extent = hasValue ? content.indexOf("=>") : content.length();
				var returnSlice = content.substring(paramEnd + 1, extent);
				var returnString = returnSlice.trim();
				var returnType = tokenizeReturnType(returnString);
				var all = withoutReturn.withReturn(returnType);
				return Optional.of(tokenizeValue(content, all))
						.map(FunctionBuilder::complete);
			} else {
				return Optional.empty();
			}
		}
		return Optional.empty();
	}

	private Type tokenizeReturnType(String returnString) {
		if (returnString.startsWith(":")) {
			var typeSlice = returnString.substring(1);
			var typeString = typeSlice.trim();
			return ContentType(typeString);
		} else {
			return ImplicitType_;
		}
	}

	private FunctionBuilder tokenizeValue(String content, FunctionBuilder.All all) {
		if (content.contains("=>")) {
			var valueIndex = content.indexOf("=>");
			var valueSlice = content.substring(valueIndex + 2);
			var valueString = valueSlice.trim();
			return all.withValue(ContentNode(valueString));
		} else {
			return all;
		}
	}

	private List<Field> tokenizeParameters(String paramsString) throws TokenizationException {
		var list = splitParameters(paramsString);
		var parameters = new ArrayList<Field>();
		for (String paramString : list) {
			var option = FieldTokenizer_.tokenize(paramString);
			if (option.isPresent()) {
				parameters.add(option.get());
			} else {
				throw TokenizationException("Invalid parameter: " + paramString);
			}
		}
		return parameters;
	}

	private ArrayList<String> splitParameters(String paramsString) {
		var list = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < paramsString.length(); i++) {
			var c = paramsString.charAt(i);
			if (c == ',' && depth == 0) {
				list.add(buffer.toString());
				buffer = new StringBuilder();
			} else {
				if (c == '(') depth++;
				if (c == ')') depth--;
				buffer.append(c);
			}
		}
		list.add(buffer.toString());
		list.removeIf(String::isBlank);
		return list;
	}

	private FunctionBuilder.WithName tokenizeHeader(String header) {
		if (header.contains(" ")) {
			var space = header.indexOf(' ');
			var flagSlice = header.substring(0, space);
			var flagString = flagSlice.trim();
			var flags = tokenizeFlags(flagString);
			var nameSlice = header.substring(space + 1);
			var name = nameSlice.trim();
			return FunctionBuilder.withFlags(flags).withName(name);
		} else {
			return FunctionBuilder.withName(header);
		}
	}

	private Set<Field.Flag> tokenizeFlags(String flagString) {
		return Arrays.stream(flagString.split(" "))
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(String::toUpperCase)
				.map(this::tokenizeFlag)
				.flatMap(Optional::stream)
				.collect(Collectors.toSet());
	}

	private Optional<Field.Flag> tokenizeFlag(String s) {
		try {
			return Optional.of(Field.Flag.valueOf(s));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
