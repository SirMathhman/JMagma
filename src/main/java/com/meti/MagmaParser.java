package com.meti;

import java.util.List;

import static com.meti.DeclarationParser.DeclarationParser_;

public class MagmaParser extends CompoundParser {
	public static final Parser MagmaParser_ = new MagmaParser();

	public MagmaParser() {
	}

	@Override
	protected List<Parser> listParsers() {
		return List.of(DeclarationParser_);
	}
}
