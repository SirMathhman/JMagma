package com.meti.compile;

import com.meti.compile.feature.condition.ConditionLexer;
import com.meti.compile.feature.function.FunctionLexer;
import com.meti.compile.feature.function.InvocationLexer;
import com.meti.compile.feature.function.ReturnLexer;
import com.meti.compile.feature.primitive.QuantityLexer;
import com.meti.compile.feature.reference.DereferenceLexer;
import com.meti.compile.feature.reference.ReferenceLexer;
import com.meti.compile.feature.scope.AssignmentLexer;
import com.meti.compile.feature.scope.BlockLexer;
import com.meti.compile.feature.scope.DeclarationLexer;
import com.meti.compile.feature.structure.AcccessorLexer;
import com.meti.compile.feature.structure.ConstructionLexer;
import com.meti.compile.feature.structure.StructureLexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MagmaCompiler implements Compiler {
	public static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	public static Stream<String> splitSequence(String sequence) {
		return Arrays.stream(sequence.split(","));
	}

	public static ArrayList<String> split(String content) {
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

	@Override
	public String compileNode(String line) {
		if (BlockLexer.BlockLexer_.canLex(line)) {
			return BlockLexer.BlockLexer_.lex(line, this);
		} else if (FunctionLexer.isFunction(line)) {
			return FunctionLexer.compileFunction(line, this);
		} else if (DeclarationLexer.isDeclaration(line)) {
			return DeclarationLexer.compileDeclaration(line, this);
		} else if (ConditionLexer.isIf(line)) {
			return ConditionLexer.compileIf(line, this);
		} else if (ConditionLexer.isWhile(line)) {
			return ConditionLexer.compileWhile(line, this);
		} else if (ConditionLexer.isElse(line)) {
			return ConditionLexer.compileElse(line, this);
		} else if (ConditionLexer.isElif(line)) {
			return ConditionLexer.compileElif(line, this);
		} else if (line.equals("true")) {
			return "1";
		} else if (line.equals("false")) {
			return "0";
		} else if (ReturnLexer.isReturn(line)) {
			return ReturnLexer.compileReturn(line, this);
		} else if (InvocationLexer.isInvocation(line)) {
			return InvocationLexer.compileInvocation(line, this);
		} else if (StructureLexer.isStructure(line)) {
			return StructureLexer.compileStructure(line, this);
		} else if (ConstructionLexer.isConstruction(line)) {
			return ConstructionLexer.compileConstruction(line, this);
		} else if (AcccessorLexer.isAccessor(line)) {
			return AcccessorLexer.compileAccesor(line, this);
		} else if (ReferenceLexer.isReference(line)) {
			return ReferenceLexer.compileReference(line, this);
		} else if (DereferenceLexer.isDereference(line)) {
			return DereferenceLexer.compileDereference(line, this);
		} else if (QuantityLexer.isQuantity(line)) {
			return QuantityLexer.compileQuantity(line, this);
		} else if (AssignmentLexer.isAssignment(line)) {
			return AssignmentLexer.compileAssignment(line, this);
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