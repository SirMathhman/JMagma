package com.meti.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.compile.feature.condition.ElifLexer.ElifLexer_;
import static com.meti.compile.feature.condition.ElseLexer.ElseLexer_;
import static com.meti.compile.feature.condition.IfLexer.IfLexer_;
import static com.meti.compile.feature.condition.WhileLexer.WhileLexer_;
import static com.meti.compile.feature.function.FunctionLexer.FunctionLexer_;
import static com.meti.compile.feature.function.InvocationLexer.InvocationLexer_;
import static com.meti.compile.feature.function.ReturnLexer.ReturnLexer_;
import static com.meti.compile.feature.primitive.BooleanLexer.BooleanLexer_;
import static com.meti.compile.feature.primitive.QuantityLexer.QuantityLexer_;
import static com.meti.compile.feature.reference.DereferenceLexer.DereferenceLexer_;
import static com.meti.compile.feature.reference.ReferenceLexer.ReferenceLexer_;
import static com.meti.compile.feature.scope.AssignmentLexer.AssignmentLexer_;
import static com.meti.compile.feature.scope.BlockLexer.BlockLexer_;
import static com.meti.compile.feature.scope.DeclarationLexer.DeclarationLexer_;
import static com.meti.compile.feature.structure.AcccessorLexer.AccessorLexer_;
import static com.meti.compile.feature.structure.ConstructionLexer.ConstructionLexer_;
import static com.meti.compile.feature.structure.StructureLexer.StructureLexer_;

public class MagmaCompiler implements Compiler {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	public static Stream<String> splitSequence(String sequence) {
		return Arrays.stream(sequence.split(","));
	}

	@Override
	public String compileField(String line) {
		var typeSeparator = line.indexOf(':');
		var valueSeparator = line.indexOf('=');
		var keysSlice = line.substring(0, typeSeparator);
		var keysString = keysSlice.trim();
		var space = keysString.lastIndexOf(' ');
		var nameSlice = keysString.substring(space + 1);
		var name = nameSlice.trim();
		var extent = valueSeparator == -1 ? line.length() : valueSeparator;
		var typeSlice = line.substring(typeSeparator + 1, extent);
		var typeString = typeSlice.trim();
		if (valueSeparator == -1) {
			return "%s %s".formatted(compileType(typeString), name);
		} else {
			var valueSlice = line.substring(valueSeparator + 1);
			var valueString = valueSlice.trim();
			var value = compileNode(valueString);
			return "%s %s=%s".formatted(compileType(typeString), name, value);
		}
	}

	@Override
	public String compileAll(String content) {
		return split(content).stream()
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(this::compileNode)
				.collect(Collectors.joining());
	}

	private static List<String> split(String content) {
		var lines = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < content.length(); i++) {
			var c = content.charAt(i);
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

	@Override
	public String compileNode(String line) {
		if (BooleanLexer_.canLex(line)) {
			return BooleanLexer_.lex(line, this);
		} else if (BlockLexer_.canLex(line)) {
			return BlockLexer_.lex(line, this);
		} else if (FunctionLexer_.canLex(line)) {
			return FunctionLexer_.lex(line, this);
		} else if (DeclarationLexer_.canLex(line)) {
			return DeclarationLexer_.lex(line, this);
		} else if (IfLexer_.canLex(line)) {
			return IfLexer_.lex(line, this);
		} else if (WhileLexer_.canLex(line)) {
			return WhileLexer_.lex(line, this);
		} else if (ElseLexer_.canLex(line)) {
			return ElseLexer_.lex(line, this);
		} else if (ElifLexer_.canLex(line)) {
			return ElifLexer_.lex(line, this);
		} else if (ReturnLexer_.canLex(line)) {
			return ReturnLexer_.lex(line, this);
		} else if (InvocationLexer_.canLex(line)) {
			return InvocationLexer_.lex(line, this);
		} else if (StructureLexer_.canLex(line)) {
			return StructureLexer_.lex(line, this);
		} else if (ConstructionLexer_.canLex(line)) {
			return ConstructionLexer_.lex(line, this);
		} else if (AccessorLexer_.canLex(line)) {
			return AccessorLexer_.lex(line, this);
		} else if (ReferenceLexer_.canLex(line)) {
			return ReferenceLexer_.lex(line, this);
		} else if (DereferenceLexer_.canLex(line)) {
			return DereferenceLexer_.lex(line, this);
		} else if (QuantityLexer_.canLex(line)) {
			return QuantityLexer_.lex(line, this);
		} else if (AssignmentLexer_.canLex(line)) {
			return AssignmentLexer_.lex(line, this);
		} else {
			return line;
		}
	}

	@Override
	public String compileType(String content) {
		if (content.equals("I16")) return "int";
		return content;
	}
}