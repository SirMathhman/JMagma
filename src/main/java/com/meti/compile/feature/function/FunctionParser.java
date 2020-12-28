package com.meti.compile.feature.function;

import com.meti.api.core.EF1;
import com.meti.api.core.Supplier;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ConditionalProcessor;
import com.meti.compile.process.ParseException;
import com.meti.compile.process.Processor;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import static com.meti.compile.process.MagmaResolver.MagmaResolver_;

public class FunctionParser implements ConditionalProcessor {
	public static final Processor FunctionParser_ = new FunctionParser();

	public FunctionParser() {
	}

	@Override
	public Node processImpl(Script script, Node node) throws ParseException {
		EF1<Field, Field, ParseException> withIdentity = identity -> {
			EF1<Type, Type, ParseException> withFunctionType = functionType -> {
				EF1<Type, Type, ParseException> withReturnType = returnType -> {
					if (returnType.is(Type.Group.Implicit)) {
						EF1<Node, Type, ParseException> resolve = body -> {
							Supplier<ParseException> invalidBody = () -> new ParseException("Invalid function body: " + body);
							return MagmaResolver_.resolve(body).orElseThrow(invalidBody);
						};
						var list = node.applyToChildrenExceptionally(resolve);
						if (list.isEmpty()) {
							throw new ParseException("No body was found in the function to resolve.");
						} else {
							return list.get(0);
						}
					}
					return returnType;
				};
				return functionType.mapByChildExceptionally(withReturnType);
			};
			return identity.mapByType(withFunctionType);
		};
		return node.mapByFieldsExceptionally(withIdentity);
	}

	@Override
	public boolean canProcess(Script script, Node node) {
		return node.is(Node.Group.Function);
	}
}
