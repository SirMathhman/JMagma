package com.meti.compile.stage;

import com.meti.compile.CLang.Formats;
import com.meti.compile.CompileException;
import com.meti.compile.io.MapMapping;
import com.meti.compile.io.Result;
import com.meti.compile.io.Source;
import com.meti.compile.token.Content;
import com.meti.compile.token.Parent;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.meti.compile.CLang.Formats.Header;
import static com.meti.compile.feature.directive.Directive.DEFINE;
import static com.meti.compile.feature.directive.Directive.IFNDEF;
import static com.meti.compile.stage.CNodeRenderer.CNodeRenderer_;
import static com.meti.compile.token.GroupAttribute.*;

public class CRenderStage {
	public static final CRenderStage CRenderStage_ = new CRenderStage();

	private CRenderStage() {
	}

	public Result.Mapping apply(Source source, List<Token> tokens) throws CompileException {
		var size = source.size();
		var joinedNameBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			joinedNameBuilder.append(source.apply(i));
		}
		var joinedName = joinedNameBuilder.toString();
		var name = source.apply(size - 1);

		var imports = new ArrayList<Token>();
		var structures = new ArrayList<Token>();
		var abstractions = new ArrayList<Token>();
		var others = new ArrayList<Token>();
		for (Token token : tokens) {
			if (Tokens.is(token, Import)) {
				imports.add(token);
			} else if (Tokens.is(token, Structure)) {
				structures.add(token);
			} else if (Tokens.is(token, Abstraction)) {
				abstractions.add(token);
			} else {
				others.add(token);
			}
		}

		var mapping = new MapMapping(Collections.emptyMap())
				.with(Header, IFNDEF.apply(joinedName))
				.with(Header, DEFINE.apply(joinedName))
				.with(Formats.Source, new Content("#include \"" + name + ".h\""));
		for (Token anImport : imports) mapping = mapping.with(Header, render(anImport));
		for (Token structure : structures) mapping = mapping.with(Header, render(structure));
		for (Token abstraction : abstractions) mapping = mapping.with(Header, render(abstraction));
		for (Token other : others) mapping = mapping.with(Formats.Source, render(other));
		return mapping;
	}

	Token render(Token token) throws CompileException {
		Token rendered;
		var isContent = Tokens.is(token, Content);
		var isParent = Tokens.is(token, Parent);
		if (isContent || isParent) {
			rendered = token;
		} else {
			var optional = CNodeRenderer_.render(token);
			var rendered1 = optional.orElseThrow(() -> new CompileException("Cannot render: " + token));
			if (Tokens.is(rendered1, Parent)) {
				var lines = rendered1.apply(Token.Query.Lines).asTokenList();
				var newLines = new ArrayList<Token>();
				for (Token line : lines) {
					newLines.add(render(line));
				}
				rendered = new Parent(newLines);
			} else if(Tokens.is(rendered1, Content)) {
				return rendered1;
			} else {
				throw new CompileException("Invalid result: " + rendered1);
			}
		}
		return rendered;
	}
}
