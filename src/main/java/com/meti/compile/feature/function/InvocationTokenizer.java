package com.meti.compile.feature.function;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.content.ContentNode;
import com.meti.compile.feature.function.Invocation.Complete;

import java.util.ArrayList;
import java.util.Optional;

import static com.meti.compile.feature.content.ContentNode.ContentNode;

public class InvocationTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> InvocationTokenizer_ = new InvocationTokenizer();

	public InvocationTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		if (content.contains("(") && !content.startsWith("(") && content.endsWith(")")) {
			var separator = content.indexOf('(');
			var callerSlice = content.substring(0, separator);
			var callerString = callerSlice.trim();
			var caller = ContentNode(callerString);
			var complete = Invocation.Invocation().withCaller(caller);
			var argumentsSlice = content.substring(separator + 1, content.length() - 1);
			var argumentsString = argumentsSlice.trim();
			var list = new ArrayList<String>();
			var buffer = new StringBuilder();
			var depth = 0;
			for (int i = 0; i < argumentsString.length(); i++) {
				var c = argumentsString.charAt(i);
				if (c == ',' && depth == 0) {
					list.add(buffer.toString());
					buffer = new StringBuilder();
				} else {
					if (c == '(') depth++;
					if (c == ')') depth--;
					buffer.append(c);
				}
			}
			list.add(buffer.toString());
			list.removeIf(String::isBlank);
			var node = list.stream()
					.map(String::trim)
					.map(ContentNode::ContentNode)
					.reduce(complete, Complete::withArgument, (complete1, complete2) -> complete2)
					.complete();
			return Optional.of(node);
		}
		return Optional.empty();
	}
}
