package com.meti.compile.feature.extern;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.util.Optional;

public class ImportTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> ImportTokenizer_ = new ImportTokenizer();

	public ImportTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		if (content.startsWith("import native ")) {
			var slice = content.substring("import native ".length());
			var trim = slice.trim();
			return Optional.of(Directive.Include.toNode("<" + trim + ".h>"));
		}
		return Optional.empty();
	}
}
