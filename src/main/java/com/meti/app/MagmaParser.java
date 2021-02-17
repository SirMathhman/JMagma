package com.meti.app;

import com.meti.parse.CompoundParser;
import com.meti.parse.Parser;

import java.util.List;

import static com.meti.declare.DeclarationParser.DeclarationParser_;

public class MagmaParser extends CompoundParser {
	public static final Parser MagmaParser_ = new MagmaParser();

	public MagmaParser() {
	}

	@Override
	protected List<Parser> listParsers() {
		return List.of(DeclarationParser_);
	}
}
