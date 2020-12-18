package com.meti.compile.feature.condition;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.util.Optional;

import static com.meti.compile.feature.condition.Conditional.Conditional;
import static com.meti.compile.feature.content.ContentNode.ContentNode;

public class ConditionalTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> ConditionalTokenizer_ = new ConditionalTokenizer();

	public ConditionalTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) throws TokenizationException {
		if (content.startsWith("if") || content.startsWith("while")) {
			var conditionStart = content.indexOf('(');
			var conditionEnd = content.indexOf(')');
			var typeSlice = content.substring(0, conditionStart);
			var typeString = typeSlice.trim();
			var type = typeString.equals("if") ? Conditional.Type.IF : Conditional.Type.WHILE;
			var conditionSlice = content.substring(conditionStart + 1, conditionEnd);
			var conditionString = conditionSlice.trim();
			var condition = ContentNode(conditionString);
			var valueSlice = content.substring(conditionEnd + 1);
			var valueString = valueSlice.trim();
			var value = ContentNode(valueString);
			return Optional.of(Conditional(condition, value, type));
		}
		return Optional.empty();
	}
}
