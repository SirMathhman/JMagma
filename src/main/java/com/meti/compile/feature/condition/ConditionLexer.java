package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public abstract class ConditionLexer implements Lexer {
	public String compileCondition(String line, final String type, Compiler compiler) {
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
}