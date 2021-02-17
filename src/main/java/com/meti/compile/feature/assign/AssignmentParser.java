package com.meti.compile.feature.assign;

import com.meti.compile.parse.ParseException;
import com.meti.compile.parse.Parser;
import com.meti.compile.parse.State;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;

import java.util.Optional;

public class AssignmentParser implements Parser {
	public static final Parser AssignmentParser_ = new AssignmentParser();

	public AssignmentParser() {
	}

	@Override
	public Optional<State> parse(State state) throws ParseException {
		try {
			if (Tokens.is(state.getCurrent(), Token.Type.Assignment)) {
				var destination = state.getCurrent().apply(Attribute.Name.Destination).computeToken();
				if (Tokens.is(destination, Token.Type.Variable)) {
					var stack = state.getStack();
					var input = destination.apply(Attribute.Name.Value).computeInput();
					var identity = stack.apply(input.compute()).orElseThrow(() -> new ParseException(input + " is not defined."));
					if (identity.isFlagged(Field.Flag.CONST)) {
						throw new ParseException(input + " is a constant variable.");
					}
				}
			}
		} catch (AttributeException e) {
			throw new ParseException(e);
		}
		return Optional.empty();
	}
}
