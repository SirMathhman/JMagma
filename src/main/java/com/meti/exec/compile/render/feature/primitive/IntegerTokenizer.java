package com.meti.exec.compile.render.feature.primitive;

import com.meti.api.collect.string.Strings;
import com.meti.api.core.FormatException;
import com.meti.api.core.Option;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.feature.Tokenizer;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.exec.compile.render.feature.primitive.Integer.Integer;

public class IntegerTokenizer implements Tokenizer {
	private final String content;

	private IntegerTokenizer(String content) {
		this.content = content;
	}

	public static IntegerTokenizer IntegerTokenizer(String content) {
		return new IntegerTokenizer(content);
	}

	@Override
	public Option<Node<?>> tokenize() {
		try {
			var value = Strings.asInt(content);
			return Some(Integer(value));
		} catch (FormatException e) {
			return None();
		}
	}
}
