package com.meti.compile.call.function;

import com.meti.api.collect.List;
import com.meti.api.stream.StreamException;
import com.meti.compile.Type;
import com.meti.compile.content.ContentType;
import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.tokenize.TokenizeException;

import java.util.Optional;

import static com.meti.api.collect.ArrayList.ArrayList;
import static com.meti.api.stream.ListStream.ListStream;

public class FunctionTypeTokenizer extends AbstractTokenizer<Type> {
	public FunctionTypeTokenizer(String content) {
		super(content);
	}

	@Override
	public Optional<Type> tokenizeExceptionally() throws TokenizeException {
		if (content.contains("=>")) {
			return Optional.of(complete());
		}
		return Optional.empty();
	}

	private Type complete() throws TokenizeException {
		FunctionType.Prototype builder = FunctionType.FunctionType()
				.withReturn(tokenizeReturn());
		String parameterSlice = content.substring(0, content.indexOf("=>"));
		String parameterTrim = parameterSlice.trim();
		return parameterTrim.startsWith("(") && parameterTrim.endsWith(")") ?
				completeWithParameters(builder, parameterTrim) :
				completeWithParameter(builder, parameterTrim);
	}

	private Type tokenizeReturn() {
		int separator = content.indexOf("=>");
		String returnSlice = content.substring(separator + 2);
		String returnTrim = returnSlice.trim();
		return new ContentType(returnTrim);
	}

	private Type completeWithParameter(FunctionType.Prototype builder, String parameterTrim) {
		return builder.withParameter(new ContentType(parameterTrim)).complete();
	}

	private Type completeWithParameters(FunctionType.Prototype builder, String parameterTrim) throws TokenizeException {
		String sequenceSlice = parameterTrim.substring(1, parameterTrim.length() - 1);
		String sequenceTrim = sequenceSlice.trim();
		String[] items = sequenceTrim.split(",");
		List<String> list = ArrayList(items);
		return completeWithParameters(builder, list);
	}

	private Type completeWithParameters(FunctionType.Prototype builder, List<String> list) throws TokenizeException {
		try {
			return reduceOntoBuilder(builder, list);
		} catch (StreamException e) {
			throw new TokenizeException("Failed to stream split parameters '%s'.", e);
		}
	}

	private Type reduceOntoBuilder(FunctionType.Prototype builder, List<String> list) throws StreamException {
		return ListStream(list)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(ContentType::new)
				.foldLeft(builder, FunctionType.FunctionTypeBuilder::withParameter)
				.complete();
	}
}
