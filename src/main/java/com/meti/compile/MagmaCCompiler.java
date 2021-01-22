package com.meti.compile;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.StreamException;
import com.meti.api.magma.core.F2E1;
import com.meti.api.magma.io.IOException_;
import com.meti.compile.io.*;
import com.meti.compile.stack.MapStack;
import com.meti.compile.token.Attribute;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.meti.compile.content.BracketSplitter.BracketSplitter_;
import static com.meti.compile.io.MapResults.MapResult;
import static com.meti.compile.stage.CRenderStage.CRenderStage_;
import static com.meti.compile.stage.MagmaFlatteningStage.MagmaFlatteningStage_;
import static com.meti.compile.stage.MagmaLexerStage.MagmaLexerStage_;
import static com.meti.compile.stage.MagmaParsingStage.MagmaParsingStage_;

public class MagmaCCompiler implements Compiler {
	public static final Compiler MagmaCCompiler_ = new MagmaCCompiler();

	private MagmaCCompiler() {
	}

	@Override
	public Result compile(Loader loader) throws CompileException {
		try {
			var folder = (F2E1<State, Source, State, CompileException>) (state, source) -> {
				try {
					return compile(state, source, loader.load(source));
				} catch (IOException_ ioException_) {
					throw new CompileException(ioException_);
				}
			};
			return Lists.stream(loader.listSources())
					.fold(new State(MapResult(), new MapStack()), folder)
					.complete();
		} catch (StreamException | IOException_ e) {
			throw new CompileException(e);
		}
	}

	private State compile(State state, Source source, String content) throws CompileException {
		var tokens = lex(content);
		var imports = findImports(tokens);

		var context = state.reset(imports).attach(tokens);
		var parsed = MagmaParsingStage_.apply(context);
		var flattened = MagmaFlatteningStage_.apply(parsed);
		var mapping = CRenderStage_.apply(source, flattened);
		return state.with(source, mapping);
	}

	private Sequence<Source> findImports(Sequence<Token> tokens) throws CompileException {
		try {
			return Lists.stream(tokens)
					.filter(token -> Tokens.is(token, GroupAttribute.Import))
					.map(token -> token.apply(Token.Query.Value))
					.map(Attribute::asString)
					.map(this::createImport)
					.fold(JavaLists.empty(), List::add);
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}

	private Source createImport(String content) {
		if (content.contains(".")) {
			return new ListSource(Arrays.stream(content.split("\\."))
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.collect(Collectors.toList()));
		} else {
			return new ListSource(Collections.singletonList(content));
		}
	}

	private Sequence<Token> lex(String content) throws CompileException {
		try {
			return BracketSplitter_.stream(content)
					.map(MagmaLexerStage_::apply)
					.fold(JavaLists.empty(), List::add);
		} catch (StreamException e) {
			throw new CompileException(e);
		}
	}
}
