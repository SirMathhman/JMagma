package com.meti.compile.scope.initialize;

import com.meti.compile.Node;
import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.content.ContentNode;
import com.meti.compile.scope.field.Field;
import com.meti.compile.scope.field.FieldTokenizer;

import java.util.Optional;

public class InitializationTokenizer extends AbstractTokenizer<Node> {
	public InitializationTokenizer(String content) {
		super(content);
	}

	@Override
	public Optional<Node> tokenize() {
		if (content.contains("=")) {
			String identityString = content.substring(0, computeSeparator()).trim();
			return new FieldTokenizer(identityString)
					.tokenize()
					.filter(field -> field.is(Field.Flag.CONST) || field.is(Field.Flag.LET))
					.map(this::complete);
		}
		return Optional.empty();
	}

	private Initialization complete(Field field) {
		int separator = computeSeparator();
		String valueSlice = content.substring(separator + 1);
		String valueTrim = valueSlice.trim();
		Node value = new ContentNode(valueTrim);
		return new Initialization(field, value);
	}

	private int computeSeparator() {
		int separator = -1;
		do {
			separator = content.indexOf('=', separator + 1);
		} while(content.startsWith("=>", separator));
		return separator;
	}
}
