package com.meti.compile.lex;

import com.meti.api.magma.core.Supplier;
import com.meti.compile.token.Content;
import com.meti.compile.token.Field;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

import static com.meti.compile.lex.FieldLexer.FieldLexer_;
import static com.meti.compile.lex.MagmaNodeLexer.MagmaNodeLexer_;
import static com.meti.compile.lex.MagmaTypeLexer.MagmaTypeLexer_;

public class MagmaLexingStage implements LexingStage {
	public static final MagmaLexingStage MagmaLexingStage_ = new MagmaLexingStage();

	private MagmaLexingStage() {
	}

	@Override
	public Field lexField(Input input) throws LexException {
		Supplier<LexException> invalidate = () -> {
			var format = "Input of %s is not a valid field.";
			var message = format.formatted(input);
			return new LexException(message);
		};
		return FieldLexer_.lex(input).orElseThrow(invalidate);
	}

	@Override
	public Token lexNode(Input input) {
		return MagmaNodeLexer_.lex(new Input(input.getContent())).orElse(new Content(input.getContent()));
	}

	@Override
	public Token lexType(Input input) {
		return MagmaTypeLexer_.lex(new Input(input.getContent())).orElse(new Content(input.getContent()));
	}
}