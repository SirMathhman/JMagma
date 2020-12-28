package com.meti.compile.feature.function;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.api.core.Supplier;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ConditionalProcessor;
import com.meti.compile.process.MagmaResolver;
import com.meti.compile.process.ParseException;
import com.meti.compile.process.Processor;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import java.util.List;

import static com.meti.compile.process.MagmaResolver.MagmaResolver_;

public class FunctionParser implements ConditionalProcessor {
	public static final Processor FunctionParser_ = new FunctionParser();

	public FunctionParser() {
	}

	@Override
	public Node processImpl(Script script, Node node) throws ParseException {
		return node.mapByFieldsExceptionally(identity -> {
			var functionType = identity.findType().orElseThrow(() -> new ParseException("No functional type was found."));
			var returnType = functionType.findChild().orElseThrow(() -> new ParseException("No return type was found."));
			if (returnType.is(Type.Group.Implicit)) {
				EF1<Node, Type, ParseException> resolve = body -> {
					Supplier<ParseException> invalidBody = () -> new ParseException("Invalid function body: " + body);
					return MagmaResolver_.resolve(body).orElseThrow(invalidBody);
				};
				var list = node.applyToChildren(resolve);
				if(list.isEmpty()) {
					throw new ParseException("No body was found in the function to resolve.");
				} else {
					var first = list.get(0);
					var newFunctionType = functionType.replaceChild(first);
					return identity.replaceType(newFunctionType);
				}
			}
			return identity;
		});
	}

	@Override
	public boolean canProcess(Script script, Node node) {
		return node.is(Node.Group.Function);
	}
}
