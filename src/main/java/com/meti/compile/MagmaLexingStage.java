package com.meti.compile;

import com.meti.compile.token.Input;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;

import static com.meti.compile.FieldLexer.FieldLexer_;
import static com.meti.compile.MagmaNodeLexer.MagmaNodeLexer_;
import static com.meti.compile.MagmaTypeLexer.MagmaTypeLexer_;

public class MagmaLexingStage implements LexingStage {
	public static final MagmaLexingStage MagmaLexingStage_ = new MagmaLexingStage();

	private MagmaLexingStage() {
	}

	@Override
	public Field lexField(String line) {
		return FieldLexer_.lex(new Input(line)).orElse(null);
	}

	@Override
	public Token lexNode(String line) {
		return MagmaNodeLexer_.lex(new Input(line)).orElse(null);
	}

	@Override
	public Token lexType(String content) {
		return MagmaTypeLexer_.lex(new Input(content)).orElse(null);
	}
}