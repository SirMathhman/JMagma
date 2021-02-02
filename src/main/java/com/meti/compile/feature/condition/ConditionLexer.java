package com.meti.compile.feature.condition;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public abstract class ConditionLexer implements Lexer<Token> {
	protected boolean canLex(String content, String type) {
		return content.startsWith(type) && content.contains("(") && content.contains(")");
	}

	protected String lex(String content, String type) {
		var condStart = content.indexOf('(');
		var condEnd = content.indexOf(')');
		var conditionSlice = content.substring(condStart + 1, condEnd);
		var conditionString = conditionSlice.trim();
		var condition = MagmaLexingStage_.lexNode(conditionString).render().getValue();
		var bodySlice = content.substring(condEnd + 1);
		var bodyString = bodySlice.trim();
		var body = MagmaLexingStage_.lexNode(bodyString).render().getValue();
		return "%s(%s)%s".formatted(type, condition, body);
	}
}