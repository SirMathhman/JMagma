package com.meti.compile.feature.function;

import com.meti.compile.token.TokenizationException;
import com.meti.compile.token.Tokenizer;
import com.meti.compile.token.Type;

import java.util.Optional;

import static com.meti.compile.feature.function.VarArgsType.VarArgsType_;

public class VarArgsTokenizer implements Tokenizer<Type> {
	public static final Tokenizer<Type> VarArgsTokenizer_ = new VarArgsTokenizer();

	public VarArgsTokenizer() {
	}

	@Override
	public Optional<Type> tokenize(String content) throws TokenizationException {
		if (content.endsWith("...")) {
			return Optional.of(VarArgsType_);
		} else {
			return Optional.empty();
		}
	}
}
