package com.meti.compile.scope.initialize;

import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.scope.field.Field;
import com.meti.compile.scope.field.FieldTokenizer;
import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.tokenize.Tokenizer;

import java.util.Optional;

import static com.meti.api.core.Some.Some;

public class InitializationTokenizer extends AbstractTokenizer<Node> {
	public InitializationTokenizer(String content) {
		super(content);
	}

	@Override
	public Optional<Node> tokenize() {
		if (content.contains("=")) {
			return computeSeparator()
					.flatMap(this::tokenizeSeparator)
					.toJava();
		}
		return Optional.empty();
	}

	private Option<Node> tokenizeSeparator(Integer integer) {
		return Some.Some(content.substring(0, integer))
				.map(String::trim)
				.map(FieldTokenizer::new)
				.flatMap(Tokenizer::tokenizeImpl)
				.filter(field -> field.is(Field.Flag.CONST) || field.is(Field.Flag.LET))
				.map(field1 -> complete(field1, integer));
	}

	private Node complete(Field field, Integer separator) {
		String valueSlice = content.substring(separator + 1);
		String valueTrim = valueSlice.trim();
		Node value = new ContentNode(valueTrim);
		return new Initialization(field, value);
	}

	private Option<Integer> computeSeparator() {
		int separator = -1;
		do {
			separator = content.indexOf('=', separator + 1);
		} while (content.startsWith("=>", separator));
		return Some(separator).filter(i -> i != -1);
	}
}
