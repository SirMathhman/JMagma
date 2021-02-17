package com.meti.compile.feature.variable;

import com.meti.compile.parse.ParseException;
import com.meti.compile.parse.Parser;
import com.meti.compile.parse.State;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;

import java.util.Optional;

public class VariableParser implements Parser {
	public static final Parser VariableParser_ = new VariableParser();

	public VariableParser() {
	}

	@Override
	public Optional<State> parse(State state) throws ParseException {
		try {
			if (Tokens.is(state.getCurrent(), Token.Type.Variable)) {
				var name = state.getCurrent().apply(Attribute.Name.Value).computeInput().compute();
				if (!state.getStack().isDefined(name)) {
					throw new ParseException(name + " is not defined.");
				}
			}
		} catch (AttributeException e) {
			throw new ParseException(e);
		}
		return Optional.empty();
	}
}
