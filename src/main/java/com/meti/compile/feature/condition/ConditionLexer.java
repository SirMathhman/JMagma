package com.meti.compile.feature.condition;

import com.meti.compile.MagmaCompiler;

public class ConditionLexer {
	public static String compileElif(String line, MagmaCompiler compiler) {
		return compileCondition(line, "else if", compiler);
	}

	public static String compileCondition(String line, final String type, MagmaCompiler compiler) {
		var condStart = line.indexOf('(');
		var condEnd = line.indexOf(')');
		var conditionSlice = line.substring(condStart + 1, condEnd);
		var conditionString = conditionSlice.trim();
		var condition = compiler.compileNode(conditionString);
		var bodySlice = line.substring(condEnd + 1);
		var bodyString = bodySlice.trim();
		var body = compiler.compileNode(bodyString);
		return "%s(%s)%s".formatted(type, condition, body);
	}

	public static boolean isElif(String line) {
		return line.startsWith("elif");
	}

	public static String compileElse(String line, MagmaCompiler compiler) {
		var bodySlice = line.substring(4);
		var bodyString = bodySlice.trim();
		return "else%s".formatted(compiler.compileAll(bodyString));
	}

	public static boolean isElse(String line) {
		return line.startsWith("else");
	}

	public static String compileWhile(String line, MagmaCompiler compiler) {
		return compileCondition(line, "while", compiler);
	}

	public static boolean isWhile(String line) {
		return line.startsWith("while");
	}

	public static String compileIf(String line, MagmaCompiler compiler) {
		return compileCondition(line, "if", compiler);
	}

	public static boolean isIf(String line) {
		return line.startsWith("if");
	}
}