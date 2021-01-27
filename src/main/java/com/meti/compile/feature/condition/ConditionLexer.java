package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public abstract class ConditionLexer implements Lexer {
	protected String lex(String line, final String type, Compiler compiler) {
		var condStart = line.indexOf('(');
		var condEnd = line.indexOf(')');
		var conditionSlice = line.substring(condStart + 1, condEnd);
		var conditionString = conditionSlice.trim();
		var condition = MagmaLexingStage_.lexNode(conditionString, null).getValue();
		var bodySlice = line.substring(condEnd + 1);
		var bodyString = bodySlice.trim();
		var body = MagmaLexingStage_.lexNode(bodyString, compiler).getValue();
		return "%s(%s)%s".formatted(type, condition, body);
	}
}