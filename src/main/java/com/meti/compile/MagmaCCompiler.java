package com.meti.compile;

import com.meti.api.magma.io.IOException_;
import com.meti.compile.io.*;
import com.meti.compile.stack.MapStack;
import com.meti.compile.stage.StageState;
import com.meti.compile.token.GroupAttribute;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

import java.util.*;
import java.util.stream.Collectors;

import static com.meti.compile.stage.CRenderStage.CRenderStage_;
import static com.meti.compile.stage.MagmaFlatteningStage.*;
import static com.meti.compile.stage.MagmaLexerStage.MagmaLexerStage_;
import static com.meti.compile.stage.MagmaParsingStage.MagmaParsingStage_;

public class MagmaCCompiler implements Compiler {
	public static final Compiler MagmaCCompiler_ = new MagmaCCompiler();

	private MagmaCCompiler() {
	}

	@Override
	public Result compile(Loader loader) throws CompileException {
		try {
			var scripts = loader.listScripts();
			var scriptSize = scripts.size();
			var map = new HashMap<Source, Result.Mapping>();
			MapStack stack = new MapStack();
			for (int i = 0; i < scriptSize; i++) {
				var script = scripts.get(i);
				var content = loader.load(script);
				var tokens = lex(content);
				var imports = new ArrayList<Source>();
				for (Token token : tokens) {
					if (Tokens.is(token, GroupAttribute.Import)) {
						imports.add(createImport(Tokens.createContent(token)));
					}
				}
				var resetStack = stack.reset2(imports);
				var state = new StageState(resetStack, tokens);
				var parsed = MagmaParsingStage_.apply(state);
				var flattened = MagmaFlatteningStage_.apply(parsed);
				var nodes = flattened.nodes();
				var mapping = CRenderStage_.apply(nodes);
				map.put(script, mapping);
			}
			return new MapResult(map);
		} catch (IOException_ e) {
			throw new CompileException(e);
		}
	}

	private Source createImport(String content1) {
		Source import_;
		if (content1.contains(".")) {
			import_ = new ListSource(Arrays.stream(content1.split("\\."))
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.collect(Collectors.toList()));
		} else {
			import_ = new ListSource(Collections.singletonList(content1));
		}
		return import_;
	}

	private List<Token> lex(String content) throws CompileException {
		var lines = split(content);
		var tokens = new ArrayList<Token>();
		for (String line : lines) {
			var token = MagmaLexerStage_.apply(line);
			tokens.add(token);
		}
		return tokens;
	}

	private List<String> split(String content) {
		var lines = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int j = 0; j < content.length(); j++) {
			var c = content.charAt(j);
			if (c == '}' && depth == 1) {
				depth = 0;
				buffer.append('}');
				lines.add(buffer.toString());
				buffer = new StringBuilder();
			} else if (c == ';' && depth == 0) {
				lines.add(buffer.toString());
				buffer = new StringBuilder();
			} else {
				if (c == '{') depth++;
				if (c == '}') depth--;
				buffer.append(c);
			}
		}
		lines.add(buffer.toString());
		lines.removeIf(String::isBlank);
		return lines;
	}
}
