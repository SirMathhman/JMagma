package com.meti.compile;

import com.meti.compile.field.Field;

import java.util.Optional;

public class FieldTokenizer implements Tokenizer<Field> {
	@Override
	public Optional<Field> tokenize(String content) {
		return Optional.empty();
	}
}
