package com.meti.compile;

import com.meti.compile.token.Content;
import com.meti.compile.token.Field;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

import static com.meti.compile.FieldLexer.FieldLexer_;
import static com.meti.compile.MagmaNodeLexer.MagmaNodeLexer_;
import static com.meti.compile.MagmaTypeLexer.MagmaTypeLexer_;

public class MagmaLexingStage implements LexingStage {
	public static final MagmaLexingStage MagmaLexingStage_ = new MagmaLexingStage();

	private MagmaLexingStage() {
	}

	@Override
	public Field lexField(Input input) {
		return FieldLexer_.lex(new Input(input.getContent())).orElse(null);
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